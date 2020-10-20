package com.latihangoding.themovie.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import timber.log.Timber

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    init {
        Timber.d("Hello")
    }
}