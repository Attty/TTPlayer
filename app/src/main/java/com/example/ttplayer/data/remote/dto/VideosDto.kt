package com.example.ttplayer.data.remote.dto

data class Videos(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val total_results: Int,
    val url: String,
    val videos: List<Video>
) {
    fun toVideo(): List<Video> {
        return this.videos
    }
}