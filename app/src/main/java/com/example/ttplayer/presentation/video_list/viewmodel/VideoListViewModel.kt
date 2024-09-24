package com.example.ttplayer.presentation.video_list.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ttplayer.common.Resource
import com.example.ttplayer.domain.use_case.get_videos.GetVideosUseCase
import com.example.ttplayer.presentation.video_list.state.VideoListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoListViewModel @Inject constructor(
    private val getVideosUseCase: GetVideosUseCase
) : ViewModel() {
    private val _state = mutableStateOf(VideoListState())
    val state: State<VideoListState> = _state

    init {
        getVideo()
    }

    fun getVideo() = viewModelScope.launch {
        getVideosUseCase.invoke()
            .onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            videoInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            videoInfoItems = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }

                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            videoInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )

                    }
                }
            }.launchIn(this)
    }

}