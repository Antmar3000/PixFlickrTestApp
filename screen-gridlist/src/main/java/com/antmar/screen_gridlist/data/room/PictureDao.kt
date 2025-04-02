package com.antmar.screen_gridlist.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.antmar.screen_gridlist.data.room.dbo.PictureDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface PictureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pics: List<PictureDbo>)

    @Query("delete from pictures")
    suspend fun clearAll()

    @Query("select * from pictures")
    fun getAll(): Flow<List<PictureDbo>>

}