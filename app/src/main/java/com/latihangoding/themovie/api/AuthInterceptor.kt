package com.latihangoding.themovie.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().header("Accept", "application/json").header(
            "Authorization",
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmYjIwOWM1ZjJhZjMwZjc0MjZmNGU4NzRmNGQ1NjIxMiIsInN1YiI6IjVkYzRmNDY1YWIxYmM3MDAxM2ZlNDA4OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.EnpkajfHJW0khwqwGEGfLrkm8PCL9O2gyx7MuXZdHyU"
        ).build()
        return chain.proceed(request)
    }
}