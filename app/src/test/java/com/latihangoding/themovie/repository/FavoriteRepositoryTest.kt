package com.latihangoding.themovie.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.latihangoding.themovie.db.FavoriteDao
import com.latihangoding.themovie.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class FavoriteRepositoryTest {

    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    private lateinit var repository: FavoriteRepository

    private val favoriteDao = mock<FavoriteDao>()

    @Before
    fun setUp() {
        repository = FavoriteRepository(favoriteDao)
    }

    @Test
    fun `checkIsFavorite exist returnTrue`() = runBlocking {
        given(favoriteDao.checkIsFavorite(2)).willReturn(1)
        val isFavorite = repository.checkIsFavorite(2)
        assertThat(isFavorite, `is`(true))
    }

    @Test
    fun `checkIsFavorite notExist returnFalse`() = runBlocking {
        given(favoriteDao.checkIsFavorite(2)).willReturn(0)
        val isFavorite = repository.checkIsFavorite(2)
        assertThat(isFavorite, `is`(false))
    }

    @Test
    fun `deleteFavorite id1 notCrash`() = runBlocking {
        repository.deleteFavorite(1)
    }

    @Test
    fun `insertFavorite favorite notCrash`() = runBlocking {
        repository.insertFavorite(mock())
    }

}