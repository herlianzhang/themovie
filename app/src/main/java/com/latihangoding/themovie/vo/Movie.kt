package com.latihangoding.themovie.vo

import com.google.gson.annotations.SerializedName

data class Movie(
    @field:SerializedName("id")
    val id: Long,
    @field:SerializedName("original_title")
    val originalTitle: String?,
    @field:SerializedName("release_date")
    val releaseDate: String?
)