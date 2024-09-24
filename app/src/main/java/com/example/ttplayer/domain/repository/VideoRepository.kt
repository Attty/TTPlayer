package com.example.ttplayer.domain.repository

import com.example.ttplayer.data.local.room.entity.VideoEntity
import com.example.ttplayer.data.remote.dto.VideosDto

interface VideoRepository {
    suspend fun getVideos(): VideosDto

    suspend fun getVideosFromDb(): List<VideoEntity>

    suspend fun insertVideosInfo(videosEntity: List<VideoEntity>)

    suspend fun deleteVideosInfo(ids: List<Int>)


}