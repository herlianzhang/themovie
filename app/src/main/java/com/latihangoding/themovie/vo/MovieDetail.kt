package com.latihangoding.themovie.vo

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @field:SerializedName("adult")
    val adult: Boolean?,
    @field:SerializedName("backdrop_path")
    val backdropPath: String?,
    @field:SerializedName("budget")
    val budget: Long?,
    @field:SerializedName("genres")
    val genres: List<Genre>?,
    @field:SerializedName("id")
    val id: Long,
    @field:SerializedName("overview")
    val overview: String?,
    @field:SerializedName("release_date")
    val releaseDate: String?,
    @field:SerializedName("runtime")
    val runtime: Int?,
    @field:SerializedName("status")
    val status: String?,
    @field:SerializedName("tagline")
    val tagline: String?,
    @field:SerializedName("title")
    val title: String?,
    @field:SerializedName("vote_average")
    val voteAverage: Float?,
    @field:SerializedName("production_companies")
    val productionCompanies: List<CommonDetail>?,
    @field:SerializedName("poster_path")
    val posterPath: String?
)

