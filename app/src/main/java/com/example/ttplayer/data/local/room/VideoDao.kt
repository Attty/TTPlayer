package com.example.ttplayer.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ttplayer.data.local.room.entity.VideoEntity

@Dao
interface VideoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideosInfo(infos: List<VideoEntity>)

    @Query("SELECT * FROM videoentity")
    suspend fun getVideosInfo(): List<VideoEntity>

    @Query("DELETE FROM videoentity WHERE id IN(:ids)")
    suspend fun deleteVideosInfo(ids: List<Int>)
}