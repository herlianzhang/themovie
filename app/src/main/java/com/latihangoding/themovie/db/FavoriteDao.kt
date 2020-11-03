package com.latihangoding.themovie.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.latihangoding.themovie.vo.Favorite

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite_table ORDER BY dateAdded DESC")
    fun getAllFavorite(): LiveData<List<Favorite>>

    @Query("SELECT COUNT(id) FROM favorite_table WHERE id = :id")
    suspend fun checkIsFavorite(id: Long): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Query("DELETE FROM favorite_table WHERE id = :id")
    suspend fun deleteFavorite(id: Long)
}