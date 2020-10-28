package com.latihangoding.themovie.ui.tv

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.latihangoding.themovie.repository.TvRepository
import javax.inject.Inject

class TvViewModel @Inject constructor(application: Application, tvRepository: TvRepository) :
    AndroidViewModel(application) {
    val tvs = tvRepository.getTv().cachedIn(viewModelScope)
}