package com.latihangoding.themovie.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.latihangoding.themovie.R
import com.latihangoding.themovie.binding.ListLoadStateAdapter
import com.latihangoding.themovie.databinding.FragmentMovieBinding
import com.latihangoding.themovie.di.Injectable
import com.latihangoding.themovie.di.injectViewModel
import com.latihangoding.themovie.vo.Movie
import kotlinx.android.synthetic.main.layout_error.view.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MovieFragment : Fragment(), Injectable, MovieAdapter.OnClickListener {

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
        viewModel = injectViewModel(viewModelFactory)

        initAdapter()
    }

    private fun initAdapter() {
        val mAdapter = MovieAdapter(this)

        binding.rvMain.adapter = mAdapter.withLoadStateFooter(
            footer = ListLoadStateAdapter { mAdapter.retry() }
        )

        mAdapter.addLoadStateListener { loadState ->
            // Only show the list if refresh succeeds.
            binding.rvMain.isVisible = loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            binding.sflLoading.isVisible = loadState.source.refresh is LoadState.Loading

            binding.iError.isVisible = loadState.source.refresh is LoadState.Error

            if (loadState.source.refresh is LoadState.Error) binding.iError.lav_error.playAnimation()
            else {
                binding.iError.lav_error.pauseAnimation()
                binding.iError.lav_error.progress = 0f
            }

            // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    activity,
                    "\uD83D\uDE28 Wooops,\nPlease check your connection",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        binding.iError.b_retry.setOnClickListener {
            mAdapter.retry()
        }

        lifecycleScope.launch {
            viewModel.movies.collectLatest {
                mAdapter.submitData(it)
            }
        }
    }

    override fun onListClicked(item: Movie) {
        Timber.d("Clicked ${item.title}")
    }

    override fun onResume() {
        super.onResume()
        binding.sflLoading.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        binding.sflLoading.stopShimmer()
    }
}