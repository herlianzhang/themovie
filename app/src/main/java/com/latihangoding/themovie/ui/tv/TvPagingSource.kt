package com.latihangoding.themovie.ui.tv

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.latihangoding.themovie.api.ApiService
import com.latihangoding.themovie.utils.EspressoIdlingResource
import com.latihangoding.themovie.vo.Tv
import retrofit2.HttpException
import java.io.IOException

class TvPagingSource(private val service: ApiService) : PagingSource<Int, Tv>() {

    private val startingPageIndex = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Tv> {
        val position = params.key ?: startingPageIndex
        EspressoIdlingResource.increment()
        return try {
            val mParams = HashMap<String, Any>()
            mParams["page"] = position
            val response = service.getTv(mParams)
            val data = response.body()?.data ?: emptyList()
            LoadResult.Page(
                data = data,
                prevKey = if (position == startingPageIndex) null else position - 1,
                nextKey = if (data.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } finally {
            EspressoIdlingResource.decrement()
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Tv>): Int? {
        return null
    }

}