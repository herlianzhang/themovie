package com.latihangoding.themovie.repository

import com.latihangoding.themovie.db.FavoriteDao
import com.latihangoding.themovie.vo.Favorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val favoriteDao: FavoriteDao) {
    fun getAllFavorite() = favoriteDao.getAllFavorite()

    suspend fun checkIsFavorite(id: Long): Boolean =
        withContext(Dispatchers.IO) {
            favoriteDao.checkIsFavorite(id) == 1
        }

    suspend fun deleteFavorite(id: Long) = favoriteDao.deleteFavorite(id)

    suspend fun insertFavorite(favorite: Favorite) = favoriteDao.insertFavorite(favorite)
}