package com.example.ttplayer.domain.use_case.get_videos

import com.example.ttplayer.common.Resource
import com.example.ttplayer.data.remote.dto.VideoDto
import com.example.ttplayer.domain.model.Video
import com.example.ttplayer.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetVideosUseCase @Inject constructor(
    private val repository: VideoRepository
) {
    operator fun invoke(): Flow<Resource<List<Video>>> = flow {
        emit(Resource.Loading())
        val videosDb = repository.getVideosFromDb().map { it.toVideo() }
        emit(Resource.Loading(data = videosDb))
        try {
            val videos = repository.getVideos().toVideosDto().map { it.toVideo() }
            repository.deleteVideosInfo(videos.map { it.id })
            repository.insertVideosInfo(videos.map { it.toVideoEntity() })
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error", data = videosDb))
        } catch (e: IOException) {
            emit(Resource.Error(data = videosDb, message = "Error internet"))
        }
        val newVideosInfo = repository.getVideosFromDb().map { it.toVideo() }
        emit(Resource.Success(newVideosInfo))
    }
}