package com.latihangoding.themovie.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.latihangoding.themovie.R
import com.latihangoding.themovie.databinding.FragmentMovieBinding
import com.latihangoding.themovie.di.Injectable
import com.latihangoding.themovie.vo.Resource
import timber.log.Timber
import javax.inject.Inject

class MovieFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)

        viewModel.movies.observe(viewLifecycleOwner, {
            when(it) {
                is Resource.SUCCESS -> {
                    Timber.d("Success ${it.data}")
                }
                is Resource.ERROR -> {
                    Timber.d("Fail ${it.e}")
                }
                is Resource.LOADING -> {
                    Timber.d("Loading")
                }
            }
        })
    }

}