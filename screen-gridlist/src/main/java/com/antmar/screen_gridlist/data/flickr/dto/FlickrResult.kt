package com.antmar.screen_gridlist.data.flickr.dto

import com.google.gson.annotations.SerializedName

data class FlickrResult(
    @SerializedName("photos")
    val photos: PhotosDto?,
    val stat: String?,
    val code: Int?,
    val message: String?,
)