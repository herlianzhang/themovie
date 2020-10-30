package com.latihangoding.themovie.ui.movie

import androidx.paging.PagingSource
import com.latihangoding.themovie.api.ApiService
import com.latihangoding.themovie.vo.Movie
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(private val service: ApiService) : PagingSource<Int, Movie>() {

    private val startingPageIndex = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: startingPageIndex
        return try {
            val mParams = HashMap<String, Any>()
            mParams["page"] = position
            val response = service.getMovie(mParams)
            val data = response.body()?.data!!
            LoadResult.Page(
                data = data,
                prevKey = if (position == startingPageIndex) null else position - 1,
                nextKey = if (data.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

}