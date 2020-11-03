package com.latihangoding.themovie.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.latihangoding.themovie.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(application: Application, favoriteRepository: FavoriteRepository) :
    AndroidViewModel(application) {
    val favorites = favoriteRepository.getAllFavorite()
}