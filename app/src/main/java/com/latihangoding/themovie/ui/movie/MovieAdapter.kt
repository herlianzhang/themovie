package com.latihangoding.themovie.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.latihangoding.themovie.databinding.ItemMovieBinding
import com.latihangoding.themovie.vo.Movie

class MovieAdapter(private val onClickListener: OnClickListener) :
    PagingDataAdapter<Movie, MovieAdapter.ViewHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) holder.bind(item, onClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    class ViewHolder private constructor(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie, onClickListener: OnClickListener) {
            binding.movie = item
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                onClickListener.onListClicked(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflate = LayoutInflater.from(parent.context)
                val binding = ItemMovieBinding.inflate(layoutInflate, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }

    interface OnClickListener {
        fun onListClicked(item: Movie)
    }
}