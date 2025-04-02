package com.antmar.screen_gridlist.data.flickr

import com.antmar.screen_gridlist.data.flickr.mapper.toDBOResult
import com.antmar.screen_gridlist.data.room.dbo.PictureDbo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlickrRepository @Inject constructor(
    private val flickrApi: FlickrApi
) {

    suspend fun search(): Result<List<PictureDbo>> = runCatching {
        return flickrApi.search().toDBOResult("m")
    }
}