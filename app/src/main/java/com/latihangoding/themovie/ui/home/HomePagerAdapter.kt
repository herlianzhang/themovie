package com.latihangoding.themovie.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.latihangoding.themovie.ui.movie.MovieFragment
import com.latihangoding.themovie.ui.tv.TvFragment
import java.lang.Exception

class HomePagerAdapter(fragment: Fragment, private val  callback: (Long, Boolean) -> Unit) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> MovieFragment(callback)
            1 -> TvFragment(callback)
            else -> throw Exception("Check getItemCount!!")
        }

}