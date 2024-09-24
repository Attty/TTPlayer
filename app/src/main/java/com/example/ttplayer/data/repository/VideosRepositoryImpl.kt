package com.example.ttplayer.data.repository

import com.example.ttplayer.data.local.room.VideoDao
import com.example.ttplayer.data.local.room.entity.VideoEntity
import com.example.ttplayer.data.remote.VideoApi
import com.example.ttplayer.data.remote.dto.VideosDto
import com.example.ttplayer.domain.repository.VideoRepository
import javax.inject.Inject

class VideosRepositoryImpl @Inject constructor(
    private val api: VideoApi,
    private val dao: VideoDao
) : VideoRepository {
    override suspend fun getVideos(): VideosDto {
        val page = (1..10).random()
        return api.getVideos(page)
    }

    override suspend fun getVideosFromDb(): List<VideoEntity> {
        return dao.getVideosInfo()
    }

    override suspend fun insertVideosInfo(videosEntity: List<VideoEntity>) {
        dao.insertVideosInfo(videosEntity)
    }

    override suspend fun deleteVideosInfo(ids: List<Int>) {
        dao.deleteVideosInfo(ids)
    }
}