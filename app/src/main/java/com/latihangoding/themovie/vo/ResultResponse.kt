package com.latihangoding.themovie.vo

import com.google.gson.annotations.SerializedName

data class ResultResponse<T>(
    @SerializedName("results")
    val data: List<T>?
)