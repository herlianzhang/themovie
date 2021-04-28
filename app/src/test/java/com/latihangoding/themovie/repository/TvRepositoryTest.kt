package com.latihangoding.themovie.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.liveData
import com.latihangoding.themovie.api.ApiResponse
import com.latihangoding.themovie.api.ApiService
import com.latihangoding.themovie.vo.Resource
import com.latihangoding.themovie.vo.TvDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import retrofit2.Response

@ExperimentalCoroutinesApi
class TvRepositoryTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: TvRepository

    private val apiService = mock<ApiService>()
    private val apiResponse = mock<ApiResponse>()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = TvRepository(apiService, apiResponse)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getTVDetail success21 returnSuccessTvDetailWithId21`() = runBlockingTest {
        val data = mock<TvDetail>()
        val tvId: Long = 21
        val responseSuccess = Response.success(data)
        val resultSuccess = liveData<Resource<TvDetail>> {
            Resource.SUCCESS(data)
        }
        given(apiService.getTvDetail(tvId)).willReturn(responseSuccess)
        given(apiResponse.getResult { responseSuccess }).willReturn(resultSuccess)
        val response = repository.getTvDetail(tvId)
        assertThat(response, `is`(resultSuccess))
    }

    @Test
    fun `getTVDetail fail21 returnError`() = runBlockingTest {
        val resultFail = liveData<Resource<TvDetail>> {
            Resource.ERROR<TvDetail>(mock())
        }
        given(apiResponse.getResult { apiService.getTvDetail(21) }).willReturn(resultFail)
        val response = repository.getTvDetail(21)
        assertThat(response, `is`(resultFail))
    }
}