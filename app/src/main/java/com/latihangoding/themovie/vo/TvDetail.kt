package com.latihangoding.themovie.vo

import com.google.gson.annotations.SerializedName

data class TvDetail(
    @field:SerializedName("backdrop_path")
    val backdropPath: String?,
    @field:SerializedName("first_air_date")
    val firstAirDate: String?,
    @field:SerializedName("genres")
    val genres: List<Genre>?,
    @field:SerializedName("id")
    val id: Long,
    @field:SerializedName("name")
    val name: String?,
    @field:SerializedName("overview")
    val overview: String?,
    @field:SerializedName("vote_average")
    val voteAverage: Float?,
    @field:SerializedName("created_by")
    val createdBy: List<CommonDetail>?,
    @field:SerializedName("production_companies")
    val productionCompanies: List<CommonDetail>?,
    @field:SerializedName("poster_path")
    val posterPath: String?
)