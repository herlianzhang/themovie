package com.latihangoding.themovie.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    init {
        Timber.d("Hello")
    }
}