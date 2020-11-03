package com.latihangoding.themovie.di

import android.app.Application
import androidx.room.Room
import com.latihangoding.themovie.BuildConfig
import com.latihangoding.themovie.api.ApiService
import com.latihangoding.themovie.api.AuthInterceptor
import com.latihangoding.themovie.db.FavoriteDao
import com.latihangoding.themovie.db.FavoriteDatabase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [CoreDataModule::class, ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideApiService(
        @ServiceApi okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okHttpClient, converterFactory, ApiService::class.java)

    @ServiceApi
    @Provides
    fun providePrivateOkHttpClient(upstreamClient: OkHttpClient): OkHttpClient =
        upstreamClient.newBuilder().addInterceptor(AuthInterceptor()).build()

    @Singleton
    @Provides
    fun provideDb(app: Application): FavoriteDatabase {
        return Room.databaseBuilder(app, FavoriteDatabase::class.java, "favorite.db")
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideFavoriteDao(db: FavoriteDatabase): FavoriteDao {
        return db.favoriteDao()
    }

    private fun createRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun <T> provideService(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
        clazz: Class<T>
    ): T = createRetrofit(okHttpClient, converterFactory).create(clazz)
}