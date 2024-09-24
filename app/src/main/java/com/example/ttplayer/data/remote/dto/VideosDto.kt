package com.example.ttplayer.data.remote.dto

data class VideosDto(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val total_results: Int,
    val url: String,
    val videos: List<VideoDto>
) {
    fun toVideosDto(): List<VideoDto> {
        return this.videos
    }
}