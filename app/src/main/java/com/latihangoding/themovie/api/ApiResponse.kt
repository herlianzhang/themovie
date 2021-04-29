package com.latihangoding.themovie.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.latihangoding.themovie.utils.EspressoIdlingResource
import com.latihangoding.themovie.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiResponse @Inject constructor() {
    fun <T> getResult(call: suspend() -> retrofit2.Response<T>): LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {
            EspressoIdlingResource.increment()
            emit(Resource.LOADING<T>())
            try {
                val response = withContext(Dispatchers.IO) { call() }
                if (response.isSuccessful) {
                    val body = response.body()
                    emit(Resource.SUCCESS(body))
                } else {
                    throw Exception(response.message())
                }
            } catch (e: Exception) {
                emit(Resource.ERROR<T>(e))
            } finally {
                EspressoIdlingResource.decrement()
            }
        }
}