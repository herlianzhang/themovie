package com.latihangoding.themovie.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.latihangoding.themovie.repository.MovieRepository
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    movieRepository: MovieRepository
) : ViewModel() {
    val movies = movieRepository.getMovie().cachedIn(viewModelScope)
}