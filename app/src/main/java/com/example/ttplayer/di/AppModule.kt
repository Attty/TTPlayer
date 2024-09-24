package com.example.ttplayer.di

import android.app.Application
import androidx.room.Room
import com.example.ttplayer.data.local.room.VideoDao
import com.example.ttplayer.data.local.room.VideoDatabase
import com.example.ttplayer.data.remote.VideoApi
import com.example.ttplayer.data.repository.VideosRepositoryImpl
import com.example.ttplayer.domain.repository.VideoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideVideoApi(): VideoApi {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader(
                        "Authorization",
                        "BIlsvgbp6xKexuyyVD4KXSshzOSdv3bb5Fv9kSVDGEFwck2Rzb1Amkbz"
                    )
                    .build()
                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.pexels.com/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VideoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideVideoDao(database: VideoDatabase): VideoDao {
        return database.dao
    }

    @Provides
    @Singleton
    fun provideVideoDatabase(app: Application): VideoDatabase {
        return Room.databaseBuilder(
            app, VideoDatabase::class.java, "video_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideVideoRepository(
        api: VideoApi,
        dao: VideoDao
    ): VideoRepository {
        return VideosRepositoryImpl(api, dao)
    }
}