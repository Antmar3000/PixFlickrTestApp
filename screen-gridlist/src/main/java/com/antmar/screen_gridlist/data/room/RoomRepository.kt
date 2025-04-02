package com.antmar.screen_gridlist.data.room

import com.antmar.screen_gridlist.data.room.dbo.PictureDbo
import com.antmar.screen_gridlist.data.room.mapper.toPictureFlow
import com.antmar.core.domain.entity.Picture
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomRepository @Inject constructor(
    private val dao: PictureDao
) {

    suspend fun insertAll(list: List<PictureDbo>) {
        dao.insertAll(list)
    }

    fun getAllFromDB(): Flow<List<com.antmar.core.domain.entity.Picture>> {
        return dao.getAll().toPictureFlow()
    }

    suspend fun clearDB() {
        dao.clearAll()
    }
}