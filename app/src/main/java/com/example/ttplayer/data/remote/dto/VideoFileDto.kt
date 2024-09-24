package com.example.ttplayer.data.remote.dto

data class VideoFileDto(
    val file_type: String,
    val fps: Double,
    val height: Int,
    val id: Int,
    val link: String,
    val quality: String,
    val size: Int,
    val width: Int
)