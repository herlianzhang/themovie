package com.latihangoding.themovie.api

import com.latihangoding.themovie.vo.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiService {
    @GET("movie/popular")
    suspend fun getMovie(@QueryMap params: HashMap<String, Any>): Response<ResultResponse<Movie>>

    @GET("tv/popular")
    suspend fun getTv(@QueryMap params: HashMap<String, Any>): Response<ResultResponse<Tv>>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Long): Response<MovieDetail>

    @GET("tv/{tv_id}")
    suspend fun getTvDetail(@Path("tv_id") tvId: Long): Response<TvDetail>
}