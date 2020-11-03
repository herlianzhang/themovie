package com.latihangoding.themovie.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.latihangoding.themovie.vo.Favorite

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}