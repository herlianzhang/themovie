package com.latihangoding.themovie.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.latihangoding.themovie.api.ApiService
import com.latihangoding.themovie.ui.tv.TvPagingSource
import javax.inject.Inject

class TvRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getTv() = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 15),
        pagingSourceFactory = { TvPagingSource(apiService) }
    ).flow
}