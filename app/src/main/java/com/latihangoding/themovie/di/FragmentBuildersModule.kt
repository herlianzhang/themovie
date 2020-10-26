package com.latihangoding.themovie.di

import com.latihangoding.themovie.ui.detail.DetailFragment
import com.latihangoding.themovie.ui.favorite.FavoriteFragment
import com.latihangoding.themovie.ui.home.HomeFragment
import com.latihangoding.themovie.ui.movie.MovieFragment
import com.latihangoding.themovie.ui.tv.TvFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieFragment(): MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeTvFragment(): TvFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteFragment(): FavoriteFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailFragment
}