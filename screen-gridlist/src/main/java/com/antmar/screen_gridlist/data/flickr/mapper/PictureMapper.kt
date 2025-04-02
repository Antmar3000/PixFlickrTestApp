package com.antmar.screen_gridlist.data.flickr.mapper

import com.antmar.screen_gridlist.data.flickr.dto.FlickrResult
import com.antmar.screen_gridlist.data.flickr.dto.PhotoDto
import com.antmar.screen_gridlist.data.room.dbo.PictureDbo

// https://www.flickr.com/services/api/misc.urls.html

//fun PhotoDto.toEntity(quality: String): Picture = Picture(
//    title = title,
//    url = "https://live.staticflickr.com/${server}/${id}_${secret}_${quality}.jpg",
//)

fun PhotoDto.toDBO(sizeSuffix: String) : PictureDbo = PictureDbo(
    id = id, title = title,
    url = "https://live.staticflickr.com/${server}/${id}_${secret}_${sizeSuffix}.jpg",
    label = ""
)

fun FlickrResult.toDBOResult (quality: String) : Result<List<PictureDbo>> {
    return try {
        Result.success(photos?.photo?.map { it.toDBO(quality) } ?: emptyList())
    } catch (e : Exception) {
        Result.failure(e)
    }
}

