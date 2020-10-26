package com.latihangoding.themovie.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.latihangoding.themovie.ui.detail.DetailViewModel
import com.latihangoding.themovie.ui.favorite.FavoriteViewModel
import com.latihangoding.themovie.ui.home.HomeViewModel
import com.latihangoding.themovie.ui.movie.MovieViewModel
import com.latihangoding.themovie.ui.tv.TvViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMovieViewModel(movieViewModel: MovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TvViewModel::class)
    abstract fun bindTvViewModel(tvViewModel: TvViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavoriteViewModel(favoriteViewModel: FavoriteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}