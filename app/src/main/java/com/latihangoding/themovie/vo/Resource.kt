package com.latihangoding.themovie.vo

sealed class Resource<out T> {
    class LOADING<T> : Resource<T>()
    data class ERROR<T>(val e: Exception) : Resource<T>()
    data class SUCCESS<T>(val data: T?) : Resource<T>()
}