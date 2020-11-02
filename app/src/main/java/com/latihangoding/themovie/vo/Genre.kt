package com.latihangoding.themovie.vo

import com.google.gson.annotations.SerializedName

data class Genre(
    @field:SerializedName("id")
    val id: Long,
    @field:SerializedName("name")
    val name: String?
)