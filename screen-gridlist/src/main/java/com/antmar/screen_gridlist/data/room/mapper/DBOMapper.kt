package com.antmar.screen_gridlist.data.room.mapper

import com.antmar.screen_gridlist.data.room.dbo.PictureDbo
import com.antmar.core.domain.entity.Picture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


fun PictureDbo.toEntity(): com.antmar.core.domain.entity.Picture {
    return com.antmar.core.domain.entity.Picture(url = this.url, title = this.title)
}

fun Flow<List<PictureDbo>>.toPictureFlow(): Flow<List<com.antmar.core.domain.entity.Picture>> {
    return this.map { list ->
        list.map { dbo -> dbo.toEntity() }
    }
}