package com.antmar.screen_gridlist.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.antmar.screen_gridlist.data.room.dbo.PictureDbo

@Database(entities = [PictureDbo::class], version = 1)
abstract class PictureDatabase: RoomDatabase() {
    abstract fun getPictureDao(): PictureDao
}