package com.antmar.screen_gridlist.data.flickr.dto

data class PhotosDto(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val photo: List<PhotoDto>,
    val total: Int
)