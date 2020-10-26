package com.latihangoding.themovie.ui.movie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.latihangoding.themovie.api.ApiResponse
import com.latihangoding.themovie.api.ApiService
import com.latihangoding.themovie.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MovieViewModel @Inject constructor(application: Application, movieRepository: MovieRepository) : AndroidViewModel(application) {

    val movies = movieRepository.getMovie()
}