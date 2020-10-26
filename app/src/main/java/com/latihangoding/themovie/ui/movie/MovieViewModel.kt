package com.latihangoding.themovie.ui.movie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.latihangoding.themovie.repository.MovieRepository
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    application: Application,
    movieRepository: MovieRepository
) : AndroidViewModel(application) {
    val movies = movieRepository.getMovie().cachedIn(viewModelScope)
}