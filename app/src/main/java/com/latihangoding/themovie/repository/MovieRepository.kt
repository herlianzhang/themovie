package com.latihangoding.themovie.repository

import com.latihangoding.themovie.api.ApiResponse
import com.latihangoding.themovie.api.ApiService
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val apiResponse: ApiResponse
) {
    fun getMovie() = apiResponse.getResult { apiService.getMovie() }
}