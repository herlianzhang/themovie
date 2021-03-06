package com.latihangoding.themovie.repository

import androidx.lifecycle.liveData
import com.latihangoding.themovie.api.ApiResponse
import com.latihangoding.themovie.api.ApiService
import com.latihangoding.themovie.utils.MainCoroutineScopeRule
import com.latihangoding.themovie.vo.Resource
import com.latihangoding.themovie.vo.TvDetail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import retrofit2.Response

@ExperimentalCoroutinesApi
class TvRepositoryTest {
    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    private lateinit var repository: TvRepository

    private val apiService = mock<ApiService>()
    private val apiResponse = mock<ApiResponse>()

    @Before
    fun setUp() {
        repository = TvRepository(apiService, apiResponse)
    }

    @Test
    fun `getTVDetail success21 returnSuccessTvDetailWithId21`() = runBlockingTest {
        val data = mock<TvDetail>()
        val resultSuccess = liveData<Resource<TvDetail>> {
            Resource.SUCCESS(data)
        }
        given(apiResponse.getResult(any<suspend () -> Response<TvDetail>>())).willReturn(
            resultSuccess
        )
        val response = repository.getTvDetail(21)
        assertThat(response, `is`(resultSuccess))
    }

    @Test
    fun `getTVDetail fail21 returnError`() = runBlockingTest {
        val resultFail = liveData<Resource<TvDetail>> {
            Resource.ERROR<TvDetail>(mock())
        }
        given(apiResponse.getResult(any<suspend () -> Response<TvDetail>>())).willReturn(resultFail)
        val response = repository.getTvDetail(21)
        assertThat(response, `is`(resultFail))
    }
}