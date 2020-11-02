package com.latihangoding.themovie.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.latihangoding.themovie.BuildConfig
import com.latihangoding.themovie.R
import com.latihangoding.themovie.databinding.FragmentDetailBinding
import com.latihangoding.themovie.di.Injectable
import com.latihangoding.themovie.vo.Genre
import com.latihangoding.themovie.vo.MovieDetail
import com.latihangoding.themovie.vo.Resource
import javax.inject.Inject

class DetailFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val args: DetailFragmentArgs by navArgs()

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_detail, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
        viewModel.setMovieId(args.id)

        viewModel.movie.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.SUCCESS -> {
                    initMovieDetail(it.data)
                }
                is Resource.LOADING -> {

                }
                is Resource.ERROR -> {

                }
            }
        })

    }

    private fun initMovieDetail(movieDetail: MovieDetail?) {
        val circularProgressDrawable = CircularProgressDrawable(requireContext())
        circularProgressDrawable.setColorSchemeColors(R.attr.colorSecondary)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        val progress = (movieDetail?.voteAverage?.times(10))?.toInt() ?: 0
        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            (activity as AppCompatActivity).supportActionBar?.title = movieDetail?.title
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
        binding.apply {
            Glide.with(ivMain.context)
                .load("${BuildConfig.IMAGE_URL}${movieDetail?.backdropPath}")
                .apply(RequestOptions())
                .placeholder(circularProgressDrawable)
                .into(ivMain)


            tvInfo.text =
                setInfo(movieDetail?.releaseDate, movieDetail?.genres, movieDetail?.runtime)


            pbVote.progress = progress
            tvVote.text = "$progress%"

            tvTagline.text = movieDetail?.tagline
            tvOverview.text = movieDetail?.overview

            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setInfo(release: String?, genres: List<Genre>?, runtime: Int?): String {
        var result = "$release . "
        if (genres != null) {
            for ((index, genre) in genres.withIndex()) {
                result += "${genre.name}"
                result += if (index == genres.lastIndex) " " else ", "
            }
        }
        result += ". "
        if (runtime != null) {
            if (runtime >= 60) {
                result += "${runtime / 60}h"
            }
            if (runtime % 60 != 0) {
                result += "${runtime % 60}m"
            }
        }
        return result
    }
}