package com.latihangoding.themovie.ui.favorite

import androidx.lifecycle.ViewModel
import com.latihangoding.themovie.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(favoriteRepository: FavoriteRepository) :
    ViewModel() {
    val favorites = favoriteRepository.getAllFavorite()
}