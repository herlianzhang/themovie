package com.latihangoding.themovie.vo

import com.google.gson.annotations.SerializedName

data class Movie(
    @field:SerializedName("id")
    val id: Long,
    @field:SerializedName("title")
    val title: String?,
    @field:SerializedName("release_date")
    val releaseDate: String?,
    @field:SerializedName("poster_path")
    val posterPath: String?,
    @field:SerializedName("vote_average")
    val voteAverage: Float?
)