package com.example.ttplayer.presentation.video_list.state

import com.example.ttplayer.domain.model.Video

data class VideoListState(
    val videoInfoItems: List<Video> = emptyList(),
    val isLoading: Boolean = false
)