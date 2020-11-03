package com.latihangoding.themovie.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.latihangoding.themovie.databinding.ItemFavoriteBinding
import com.latihangoding.themovie.vo.Favorite

class FavoriteAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Favorite, FavoriteAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding(item, onClickListener)
    }

    class ViewHolder private constructor(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(item: Favorite, onClickListener: OnClickListener) {
            binding.favorite = item
            binding.executePendingBindings()
            binding.tvStatus.text = if (item.status) "Movie" else "Tv"

            binding.root.setOnClickListener {
                onClickListener.onListClicked(item.id, item.status)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemFavoriteBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Favorite>() {
        override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean =
            oldItem == newItem
    }

    interface OnClickListener {
        fun onListClicked(id: Long, status: Boolean)
    }
}