package com.latihangoding.themovie.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.latihangoding.themovie.repository.MovieRepository
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    application: Application,
    movieRepository: MovieRepository
) :
    AndroidViewModel(application) {
    private val movieId = MutableLiveData<Long>()
    val movie = Transformations.switchMap(movieId) {
        movieRepository.getMovieDetail(it)
    }

    fun setMovieId(currMovieId: Long) {
        if (currMovieId != movieId.value) {
            movieId.postValue(currMovieId)
        }
    }
}