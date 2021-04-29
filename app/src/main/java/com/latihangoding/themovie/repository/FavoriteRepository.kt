package com.latihangoding.themovie.repository

import com.latihangoding.themovie.db.FavoriteDao
import com.latihangoding.themovie.utils.EspressoIdlingResource
import com.latihangoding.themovie.vo.Favorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val favoriteDao: FavoriteDao) {
    fun getAllFavorite() = favoriteDao.getAllFavorite()

    suspend fun checkIsFavorite(id: Long): Boolean {
        EspressoIdlingResource.increment()
        val isFavorite = withContext(Dispatchers.IO) {
            favoriteDao.checkIsFavorite(id) == 1
        }
        EspressoIdlingResource.decrement()
        return isFavorite
    }


    suspend fun deleteFavorite(id: Long) {
        EspressoIdlingResource.increment()
        favoriteDao.deleteFavorite(id)
        EspressoIdlingResource.decrement()
    }

    suspend fun insertFavorite(favorite: Favorite) {
        EspressoIdlingResource.increment()
        favoriteDao.insertFavorite(favorite)
        EspressoIdlingResource.decrement()
    }
}