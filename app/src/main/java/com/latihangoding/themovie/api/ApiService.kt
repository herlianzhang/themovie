package com.latihangoding.themovie.api

import androidx.lifecycle.LiveData
import com.latihangoding.themovie.vo.Movie
import com.latihangoding.themovie.vo.ResultResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("movie/popular")
    suspend fun getMovie(): Response<ResultResponse<Movie>>
}