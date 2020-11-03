package com.latihangoding.themovie.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class Favorite(
    @PrimaryKey
    val id: Long,
    val dateAdded: Long,
    val status: Boolean,
    val poster: String?,
    val title: String?,
    val releaseDate: String?
)