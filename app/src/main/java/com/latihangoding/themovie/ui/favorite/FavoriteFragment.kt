package com.latihangoding.themovie.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.latihangoding.themovie.R
import com.latihangoding.themovie.databinding.FragmentFavoriteBinding
import com.latihangoding.themovie.di.Injectable
import com.latihangoding.themovie.di.injectViewModel
import javax.inject.Inject

class FavoriteFragment : Fragment(), Injectable, FavoriteAdapter.OnClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_favorite, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)

        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            (activity as AppCompatActivity).supportActionBar?.title = "Favorite"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        initAdapter()
    }

    private fun initAdapter() {
        val mAdapter = FavoriteAdapter(this)
        binding.rvMain.adapter = mAdapter

        viewModel.favorites.observe(viewLifecycleOwner, {
            mAdapter.submitList(it)
            binding.tvNoData.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
        })
    }

    override fun onListClicked(id: Long, status: Boolean) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(id, status)
        findNavController().navigate(action)
    }

}