package com.example.ttplayer.data.remote

import com.example.ttplayer.data.remote.dto.VideosDto
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoApi {

    @GET("/videos/search?query=nature&per_page=12&size=small&orientation=portrait")
    suspend fun getVideos(@Query("page") page: Int): VideosDto

}