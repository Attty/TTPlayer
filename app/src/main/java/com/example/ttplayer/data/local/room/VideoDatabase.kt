package com.example.ttplayer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ttplayer.data.local.entity.VideoEntity

@Database(
    entities = [VideoEntity::class],
    version = 1
)
abstract class VideoDatabase : RoomDatabase() {
    abstract val dao: VideoDao
}