package com.latihangoding.themovie.vo

import com.google.gson.annotations.SerializedName

data class CommonDetail(
    @field:SerializedName("id")
    val id: Long,
    @field:SerializedName("name")
    val name: String?,
    @field:SerializedName("profile_path")
    val profilePath: String?,
    @field:SerializedName("logo_path")
    val logoPath: String?,
    @field:SerializedName("origin_country")
    val originCountry: String?,
    @field:SerializedName("gender")
    val gender: Int?
)