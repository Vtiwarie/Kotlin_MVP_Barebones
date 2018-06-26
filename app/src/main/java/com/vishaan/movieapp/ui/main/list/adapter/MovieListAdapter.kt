package com.vishaan.movieapp.ui.main.list.adapter

import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import com.vishaan.movieapp.data.entity.Movie
import com.vishaan.movieapp.ui.base.list.BaseViewHolder
import com.vishaan.movieapp.ui.base.list.ListItem
import com.vishaan.movieapp.ui.base.list.LoadingAdapter
import com.vishaan.movieapp.ui.base.list.LoadingViewHolder

class MovieListAdapter : LoadingAdapter<Movie>() {

    var listener: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            ListItem.Type.LOADING.ordinal -> LoadingViewHolder(parent)
            else -> return MovieListViewHolder(parent, listener)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is MovieListViewHolder -> holder.bind(getItem(position))
            is LoadingViewHolder -> holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            isLoadingType(position) -> ListItem.Type.LOADING.ordinal
            else -> ListItem.Type.MOVIELIST.ordinal
        }
    }

    override fun createDiffCallback() = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onInserted(position: Int, count: Int) {
        when {
            isLoading -> notifyDataSetChanged()
            else -> super.onInserted(position, count)
        }
    }
}
