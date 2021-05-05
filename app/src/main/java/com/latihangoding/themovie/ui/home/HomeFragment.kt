package com.latihangoding.themovie.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.latihangoding.themovie.R
import com.latihangoding.themovie.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var homePagerAdapter: HomePagerAdapter

    private lateinit var pageChangeCallback: ViewPager2.OnPageChangeCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        initOnClick()
        initPagerAdapter()

        return binding.root
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
        homePagerAdapter = HomePagerAdapter(this)
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
                (activity as AppCompatActivity).supportActionBar?.title =
                    if (position == 0) "Movie" else "Tv"
            }
        }
        binding.pager.apply {
            adapter = homePagerAdapter
            if (::pageChangeCallback.isInitialized)
                registerOnPageChangeCallback(pageChangeCallback)
        }
    }

    override fun onDestroy() {
        if (::pageChangeCallback.isInitialized)
            binding.pager.unregisterOnPageChangeCallback(pageChangeCallback)
        super.onDestroy()
    }
}