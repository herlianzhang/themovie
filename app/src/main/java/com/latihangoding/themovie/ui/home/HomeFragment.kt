package com.latihangoding.themovie.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.latihangoding.themovie.R
import com.latihangoding.themovie.databinding.FragmentHomeBinding
import com.latihangoding.themovie.di.Injectable
import com.latihangoding.themovie.di.injectViewModel
import javax.inject.Inject

class HomeFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    private lateinit var homePagerAdapter: HomePagerAdapter
    private lateinit var pageChangeCallback: ViewPager2.OnPageChangeCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        initOnClick()
        initPagerAdapter()
    }

    private fun initOnClick() {
        binding.apply {
            ibMovie.setOnClickListener {
                pager.currentItem = 0
            }
            ibTv.setOnClickListener {
                pager.currentItem = 1
            }
            fabFavorite.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
            }
        }

    }

    private fun initPagerAdapter() {
        homePagerAdapter = HomePagerAdapter(this, goToDetail)
        pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.ibMovie.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        if (position == 0) R.color.teal_200 else R.color.white
                    )
                )
                binding.ibTv.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        if (position == 1) R.color.teal_200 else R.color.white
                    )
                )
                (activity as AppCompatActivity).supportActionBar?.title = if (position == 0) "Movie" else "Tv"
            }
        }
        binding.pager.apply {
            adapter = homePagerAdapter
            registerOnPageChangeCallback(pageChangeCallback)
        }
    }

    override fun onDestroy() {
        binding.pager.unregisterOnPageChangeCallback(pageChangeCallback)
        super.onDestroy()
    }

    /*
        status true = Movie
        status false = TV
    */
    private val goToDetail = { id: Long, status: Boolean ->
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(id, status)
        findNavController().navigate(action)
    }
}