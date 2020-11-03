package com.latihangoding.themovie.db

import androidx.room.*
import com.latihangoding.themovie.vo.Favorite

@Dao
interface FavoriteDao {
//    @Query("SELECT COUNT(id) favorite_table WHERE id = :id")
//    suspend fun filter(id: Long): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)
}