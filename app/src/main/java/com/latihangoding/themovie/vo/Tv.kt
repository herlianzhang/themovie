package com.latihangoding.themovie.vo

import com.google.gson.annotations.SerializedName

data class Tv(
    @field:SerializedName("id")
    val id: Long?,
    @field:SerializedName("name")
    val name: String?,
    @field:SerializedName("poster_path")
    val posterPath: String?,
    @field:SerializedName("vote_average")
    val voteAverage: Float?,
    @field:SerializedName("first_air_date")
    val firstAirDate: String?
)