package com.latihangoding.themovie.ui.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.latihangoding.themovie.databinding.ItemTvBinding
import com.latihangoding.themovie.vo.Tv

class TvAdapter(private val onClickListener: OnClickListener) :
    PagingDataAdapter<Tv, TvAdapter.ViewHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) holder.bind(item, onClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    class ViewHolder private constructor(private val binding: ItemTvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Tv, onClickListener: OnClickListener) {
            binding.tv = item
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                onClickListener.onListClicked(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflate = LayoutInflater.from(parent.context)
                val binding = ItemTvBinding.inflate(layoutInflate, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Tv>() {
        override fun areItemsTheSame(oldItem: Tv, newItem: Tv): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Tv, newItem: Tv): Boolean =
            oldItem == newItem
    }

    interface OnClickListener {
        fun onListClicked(item: Tv)
    }
}