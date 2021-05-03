package com.latihangoding.themovie.ui.tv

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
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.paging.LoadState
import androidx.viewpager2.widget.ViewPager2
import com.latihangoding.themovie.R
import com.latihangoding.themovie.binding.ListLoadStateAdapter
import com.latihangoding.themovie.databinding.FragmentTvBinding
import com.latihangoding.themovie.di.Injectable
import com.latihangoding.themovie.di.injectViewModel
import com.latihangoding.themovie.ui.home.HomeFragmentDirections
import com.latihangoding.themovie.vo.Tv
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class TvFragment : Fragment(), Injectable,
    TvAdapter.OnClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentTvBinding
    private lateinit var viewModel: TvViewModel

    private val mainNavController: NavController? by lazy { activity?.findNavController(R.id.fcv_nav) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_tv, container, false)
        viewModel = injectViewModel(viewModelFactory)

        initAdapter()

        return binding.root
    }

    private fun initAdapter() {
        val mAdapter = TvAdapter(this)

        binding.rvMain.adapter = mAdapter.withLoadStateFooter(
            footer = ListLoadStateAdapter { mAdapter.retry() }
        )

        mAdapter.addLoadStateListener { loadState ->
            // Only show the list if refresh succeeds.
            binding.rvMain.isVisible = loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            binding.sflLoading.isVisible = loadState.source.refresh is LoadState.Loading

            binding.iError.clMain.isVisible = loadState.source.refresh is LoadState.Error

            if (loadState.source.refresh is LoadState.Error) binding.iError.lavError.playAnimation()
            else {
                binding.iError.lavError.pauseAnimation()
                binding.iError.lavError.progress = 0f
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

        binding.iError.bRetry.setOnClickListener {
            mAdapter.retry()
        }

        lifecycleScope.launch {
            viewModel.tvs.collectLatest {
                mAdapter.submitData(it)
            }
        }
    }

    override fun onListClicked(item: Tv) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(item.id, false)
        mainNavController?.navigate(action)
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