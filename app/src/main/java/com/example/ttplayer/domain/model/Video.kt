package com.example.ttplayer.domain.model

import com.example.ttplayer.data.local.room.entity.VideoEntity

data class Video(
    val id: Int,
    val duration: Int,
    val image: String,
    val width: Int,
    val height: Int,
    val user: String,
    val video: String,
) {
    fun toVideoEntity(): VideoEntity {
        return VideoEntity(
            id, duration, image, width, height, user, video
        )
    }
}