package com.latihangoding.themovie.api

import com.latihangoding.themovie.vo.Movie
import com.latihangoding.themovie.vo.ResultResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET("movie/popular")
    suspend fun getMovie(@QueryMap params: HashMap<String, Any>): Response<ResultResponse<Movie>>
}