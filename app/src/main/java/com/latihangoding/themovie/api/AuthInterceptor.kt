package com.latihangoding.themovie.api

import com.latihangoding.themovie.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().header("Accept", "application/json").header(
            "Authorization",
            BuildConfig.TOKEN
        ).build()
        return chain.proceed(request)
    }
}