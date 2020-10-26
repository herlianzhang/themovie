package com.latihangoding.themovie.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.latihangoding.themovie.R
import com.latihangoding.themovie.databinding.FragmentDetailBinding
import com.latihangoding.themovie.di.Injectable
import javax.inject.Inject

class DetailFragment : Fragment(), Injectable {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
    }

}