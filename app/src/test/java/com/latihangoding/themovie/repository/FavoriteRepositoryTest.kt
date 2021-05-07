package com.latihangoding.themovie.repository

import com.latihangoding.themovie.db.FavoriteDao
import com.latihangoding.themovie.utils.MainCoroutineScopeRule
import com.latihangoding.themovie.vo.Favorite
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.internal.verification.Times
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

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
        verify(favoriteDao, Times(1)).deleteFavorite(1)
    }

    @Test
    fun `insertFavorite favorite notCrash`() = runBlocking {
        val favorite: Favorite = mock()
        repository.insertFavorite(favorite)
        verify(favoriteDao, Times(1)).insertFavorite(favorite)
    }

}