package com.latihangoding.themovie.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.latihangoding.themovie.api.ApiResponse
import com.latihangoding.themovie.api.ApiService
import com.latihangoding.themovie.ui.movie.MoviePagingSource
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val apiResponse: ApiResponse
) {
    fun getMovie() = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 15),
        pagingSourceFactory = { MoviePagingSource(apiService) }
    ).flow
}