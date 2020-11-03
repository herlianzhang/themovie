package com.latihangoding.themovie.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.latihangoding.themovie.R
import com.latihangoding.themovie.binding.imageUrl
import com.latihangoding.themovie.databinding.ItemCommonDetailBinding
import com.latihangoding.themovie.vo.CommonDetail

class CommonDetailAdapter :
    ListAdapter<CommonDetail, CommonDetailAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(private val binding: ItemCommonDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CommonDetail) {
            binding.commonDetail = item
            binding.executePendingBindings()
            if (item.gender != null) {
                if (item.profilePath != null) {
                    binding.ivMain.imageUrl(item.profilePath)
                } else {
                    binding.ivMain.setImageResource(if (item.gender == 1) R.drawable.ic_woman else R.drawable.ic_man)
                }
            } else {
                if (item.logoPath != null) {
                    binding.ivMain.imageUrl(item.logoPath)
                } else {
                    binding.ivMain.setImageResource(R.drawable.ic_company)
                }
            }

            binding.tvCountry.visibility =
                if (item.originCountry != null) View.VISIBLE else View.GONE
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCommonDetailBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<CommonDetail>() {
        override fun areItemsTheSame(oldItem: CommonDetail, newItem: CommonDetail): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CommonDetail, newItem: CommonDetail): Boolean =
            oldItem == newItem
    }
}