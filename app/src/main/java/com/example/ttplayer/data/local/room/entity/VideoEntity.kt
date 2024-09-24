package com.example.ttplayer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ttplayer.domain.model.Video

@Entity
data class VideoEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val duration: Int,
    val image: String,
    val width: Int,
    val height: Int,
    val user: String,
    val video: String,
){
    fun toVideo(): Video{
        return Video(
            id = id,
            duration =duration,
            image = image,
            width = width,
            height = height,
            user = user,
            video = video
        )
    }
}
