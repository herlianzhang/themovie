package com.latihangoding.themovie.binding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.latihangoding.themovie.R
import com.latihangoding.themovie.databinding.ListLoadStateFooterViewItemBinding

class ListLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<ListLoadStateAdapter.ListLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: ListLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ListLoadStateViewHolder =
        ListLoadStateViewHolder.create(parent, retry)

    class ListLoadStateViewHolder(
        private val binding: ListLoadStateFooterViewItemBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnRetry.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                pbMain.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
                tvErrorMssg.isVisible = loadState !is LoadState.Loading
            }
        }

        companion object {
            fun create(parent: ViewGroup, retry: () -> Unit): ListLoadStateViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_load_state_footer_view_item, parent, false)
                val binding = ListLoadStateFooterViewItemBinding.bind(view)
                return ListLoadStateViewHolder(binding, retry)
            }
        }
    }
}