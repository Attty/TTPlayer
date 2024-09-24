package com.example.ttplayer.data.remote.dto

import com.example.ttplayer.common.findOrFirst
import com.example.ttplayer.domain.model.Video

data class VideoDto(
    val avg_color: Any,
    val duration: Int,
    val full_res: Any,
    val height: Int,
    val id: Int,
    val image: String,
    val tags: List<Any>,
    val url: String,
    val user: UserDto,
    val video_files: List<VideoFileDto>,
    val video_pictures: List<VideoPictureDto>,
    val width: Int
) {
    fun toVideo(): Video {
        return Video(
            id = this.id,
            duration = this.duration,
            image = this.image,
            width = this.width,
            height = this.height,
            user = this.user.name,
            video = this.video_files.findOrFirst {
                it.width >= 1080 && it.height >= 1080
            }.link
        )
    }
}