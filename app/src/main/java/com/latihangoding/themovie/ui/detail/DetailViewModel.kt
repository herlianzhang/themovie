package com.latihangoding.themovie.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.latihangoding.themovie.repository.MovieRepository
import com.latihangoding.themovie.repository.TvRepository
import com.latihangoding.themovie.vo.CommonDetail
import com.latihangoding.themovie.vo.Genre
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    application: Application,
    movieRepository: MovieRepository,
    tvRepository: TvRepository
) :
    AndroidViewModel(application) {

    private val movieId = MutableLiveData<Long>()
    val movieDetail = Transformations.switchMap(movieId) {
        movieRepository.getMovieDetail(it)
    }

    private val tvId = MutableLiveData<Long>()
    val tvDetail = Transformations.switchMap(tvId) {
        tvRepository.getTvDetail(it)
    }

    private val _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String?>
        get() = _imageUrl

    private val _vote = MutableLiveData<Float>()
    val vote: LiveData<Float?>
        get() = _vote

    private val _tagline = MutableLiveData<String>()
    val tagline: LiveData<String?>
        get() = _tagline

    private val _title = MutableLiveData<String>()
    val title: LiveData<String?>
        get() = _title

    private val _info = MutableLiveData<String>()
    val info: LiveData<String?>
        get() = _info

    private val _overview = MutableLiveData<String>()
    val overview: LiveData<String?>
        get() = _overview

    private val _createdBy = MutableLiveData<List<CommonDetail>>()
    val createdBy: LiveData<List<CommonDetail>?>
        get() = _createdBy

    private val _productionCompanies = MutableLiveData<List<CommonDetail>>()
    val productionCompanies: LiveData<List<CommonDetail>?>
        get() = _productionCompanies

    fun setId(id: Long, status: Boolean) {
        if (status) {
            if (id != movieId.value) {
                movieId.postValue(id)
            }
        } else {
            if (id != tvId.value) {
                tvId.postValue(id)
            }
        }
    }

    fun setInfo(release: String?, genres: List<Genre>?, runtime: Int?) {
        var result = ""
        if (release != null) {
            result += "$release "
        }
        if (genres != null) {
            result += ". "
            for ((index, genre) in genres.withIndex()) {
                result += "${genre.name}"
                result += if (index == genres.lastIndex) " " else ", "
            }
        }
        if (runtime != null) {
            result += ". "
            if (runtime >= 60) {
                result += "${runtime / 60}h"
            }
            if (runtime % 60 != 0) {
                result += "${runtime % 60}m"
            }
        }

        _info.postValue(result)
    }

    fun setImageUrl(imageUrl: String?) {
        _imageUrl.postValue(imageUrl)
    }

    fun setVote(vote: Float?) {
        _vote.postValue(vote)
    }

    fun setTagLine(tagline: String?) {
        _tagline.postValue(tagline)
    }

    fun setTitle(title: String?) {
        _title.postValue(title)
    }

    fun setOverview(overview: String?) {
        _overview.postValue(overview)
    }

    fun setCreatedBy(createdBy: List<CommonDetail>?) {
        _createdBy.postValue(createdBy)
    }

    fun setProductionCompanies(productionCompanies: List<CommonDetail>?) {
        _productionCompanies.postValue(productionCompanies)
    }
}