package com.latihangoding.themovie.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.latihangoding.themovie.R
import com.latihangoding.themovie.binding.imageUrl
import com.latihangoding.themovie.databinding.FragmentDetailBinding
import com.latihangoding.themovie.di.Injectable
import com.latihangoding.themovie.vo.Favorite
import com.latihangoding.themovie.vo.MovieDetail
import com.latihangoding.themovie.vo.Resource
import com.latihangoding.themovie.vo.TvDetail
import javax.inject.Inject

class DetailFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val args: DetailFragmentArgs by navArgs()

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_detail, container, false)

        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
        viewModel.setId(args.id, args.status)

        initObserver()
        initRvCreatedBy()
        initProductionCompanies()
        initButton()

        return binding.root
    }

    private fun initObserver() {
        viewModel.movieDetail.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.SUCCESS -> {
                    initMovieDetail(it.data)
                    binding.pbLoading.visibility = View.GONE
                }
                is Resource.LOADING -> {
                    binding.pbLoading.visibility = View.VISIBLE
                }
                is Resource.ERROR -> {
                    binding.pbLoading.visibility = View.GONE
                    showToastError()
                }
            }
        })

        viewModel.tvDetail.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.SUCCESS -> {
                    initTvDetail(it.data)
                    binding.pbLoading.visibility = View.GONE
                }
                is Resource.LOADING -> {
                    binding.pbLoading.visibility = View.VISIBLE
                }
                is Resource.ERROR -> {
                    binding.pbLoading.visibility = View.GONE
                    showToastError()
                }
            }
        })

        viewModel.imageUrl.observe(viewLifecycleOwner, {
            binding.ivMain.imageUrl(it, R.attr.colorSecondary)
        })

        viewModel.vote.observe(viewLifecycleOwner, {
            val progress = (it?.times(10))?.toInt() ?: 0
            binding.pbVote.progress = progress
            binding.tvVote.text = getString(R.string.progress, progress)
        })

        viewModel.tagline.observe(viewLifecycleOwner, {
            if (it != null && it != "") {
                binding.tvTagline.text = it
            } else {
                binding.tvTagline.visibility = View.GONE
            }
        })

        viewModel.title.observe(viewLifecycleOwner, {
            (activity as AppCompatActivity).apply {
                setSupportActionBar(binding.toolbar)
                (activity as AppCompatActivity).supportActionBar?.title = it
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowHomeEnabled(true)
            }
            binding.toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        })

        viewModel.info.observe(viewLifecycleOwner, {
            binding.tvInfo.text = it
        })

        viewModel.overview.observe(viewLifecycleOwner, {
            binding.tvOverview.text = it
        })

        viewModel.isFavorited.observe(viewLifecycleOwner, {
            binding.fabFavorite.setImageResource(if (it) R.drawable.ic_favorite else R.drawable.ic_favorite_border)
        })
    }

    private fun initMovieDetail(movieDetail: MovieDetail?) {
        var favorite: Favorite? = null
        movieDetail?.let {
            favorite = Favorite(it.id, args.status, it.posterPath, it.title, it.releaseDate)
        }
        viewModel.apply {
            setImageUrl(movieDetail?.backdropPath)
            setVote(movieDetail?.voteAverage)
            setTagLine(movieDetail?.tagline)
            setTitle(movieDetail?.title)
            setInfo(movieDetail?.releaseDate, movieDetail?.genres, movieDetail?.runtime)
            setOverview(movieDetail?.overview)
            setCreatedBy(null)
            setProductionCompanies(movieDetail?.productionCompanies)
            setFavorite(favorite)
        }
    }

    private fun initTvDetail(tvDetail: TvDetail?) {
        var favorite: Favorite? = null
        tvDetail?.let {
            favorite = Favorite(it.id, args.status, it.posterPath, it.name, it.firstAirDate)
        }
        viewModel.apply {
            setImageUrl(tvDetail?.backdropPath)
            setVote(tvDetail?.voteAverage)
            setTagLine(null)
            setTitle(tvDetail?.name)
            setInfo(tvDetail?.firstAirDate, tvDetail?.genres, null)
            setOverview(tvDetail?.overview)
            setCreatedBy(tvDetail?.createdBy)
            setProductionCompanies(tvDetail?.productionCompanies)
            setFavorite(favorite)
        }
    }

    private fun initButton() {
        binding.apply {
            fabFavorite.setOnClickListener {
                viewModel.checkFavorite()
            }
        }
    }

    private fun initRvCreatedBy() {
        val adapter = CommonDetailAdapter()
        binding.rvCreatedBy.adapter = adapter

        viewModel.createdBy.observe(viewLifecycleOwner, {
            if (it != null && it.isNotEmpty()) {
                adapter.submitList(it)
            } else {
                binding.tvCreatedBy.visibility = View.GONE
                binding.rvCreatedBy.visibility = View.GONE
            }
        })
    }

    private fun initProductionCompanies() {
        val adapter = CommonDetailAdapter()
        binding.rvProductionCompanies.adapter = adapter

        viewModel.productionCompanies.observe(viewLifecycleOwner, {
            if (it != null && it.isNotEmpty()) {
                adapter.submitList(it)
            } else {
                binding.tvProductionCompanies.visibility = View.GONE
                binding.rvProductionCompanies.visibility = View.GONE
            }
        })
    }

    private fun showToastError() {
        Toast.makeText(requireContext(), "Something happend, try again later!", Toast.LENGTH_SHORT).show()
    }
}