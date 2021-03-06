package com.latihangoding.themovie.ui.detail

import androidx.lifecycle.*
import com.latihangoding.themovie.repository.FavoriteRepository
import com.latihangoding.themovie.repository.MovieRepository
import com.latihangoding.themovie.repository.TvRepository
import com.latihangoding.themovie.vo.CommonDetail
import com.latihangoding.themovie.vo.Favorite
import com.latihangoding.themovie.vo.Genre
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    movieRepository: MovieRepository,
    tvRepository: TvRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _favorite = MutableLiveData<Favorite>()
    val favorite: LiveData<Favorite>
        get() = _favorite

    val movieId = MutableLiveData<Long>()
    val movieDetail = Transformations.switchMap(movieId) {
        movieRepository.getMovieDetail(it)
    }

    val tvId = MutableLiveData<Long>()
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

    private val _isFavorited = MutableLiveData<Boolean>()
    val isFavorited: LiveData<Boolean>
        get() = _isFavorited

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
        imageUrl?.let {
            _imageUrl.postValue(it)
        }
    }

    fun setVote(vote: Float?) {
        vote?.let {
            _vote.postValue(it)
        }
    }

    fun setTagLine(tagline: String?) {
        tagline?.let {
            _tagline.postValue(it)
        }
    }

    fun setTitle(title: String?) {
        title?.let {
            _title.postValue(it)
        }
    }

    fun setOverview(overview: String?) {
        overview?.let {
            _overview.postValue(it)
        }
    }

    fun setCreatedBy(createdBy: List<CommonDetail>?) {
        createdBy?.let {
            _createdBy.postValue(it)
        }
    }

    fun setProductionCompanies(productionCompanies: List<CommonDetail>?) {
        productionCompanies?.let {
            _productionCompanies.postValue(it)
        }
    }

    fun setFavorite(favorite: Favorite?) {
        favorite?.let {
            _favorite.postValue(it)
            viewModelScope.launch {
                _isFavorited.postValue(favoriteRepository.checkIsFavorite(it.id))
            }
        }
    }

    fun checkFavorite() {
        favorite.value?.let {
            viewModelScope.launch {
                if (favoriteRepository.checkIsFavorite(it.id)) favoriteRepository.deleteFavorite(it.id)
                else favoriteRepository.insertFavorite(it)

                _isFavorited.postValue(favoriteRepository.checkIsFavorite(it.id))
            }
        }
    }
}