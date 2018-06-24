/*package com.enovlab.yoop.ui.main.list.adapter

import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import com.enovlab.yoop.ui.base.list.BaseViewHolder
import com.enovlab.yoop.ui.base.list.ListItem
import com.enovlab.yoop.ui.base.list.LoadingAdapter
import com.enovlab.yoop.ui.base.list.LoadingViewHolder

class MovieListAdapter : LoadingAdapter<DiscoverItem>() {

    var listener: ((DiscoverItem) -> Unit)? = null

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
            else -> ListItem.Type.DISCOVER.ordinal
        }
    }

    override fun createDiffCallback() = object : DiffUtil.ItemCallback<DiscoverItem>() {
        override fun areItemsTheSame(oldItem: DiscoverItem, newItem: DiscoverItem): Boolean {
            return oldItem.event.id == newItem.event.id
        }

        override fun areContentsTheSame(oldItem: DiscoverItem, newItem: DiscoverItem): Boolean {
            return oldItem.event.name == newItem.event.name
                && oldItem.event.date == newItem.event.date
                && oldItem.event.locationName == newItem.event.locationName
                && oldItem.pills == newItem.pills
        }
    }

    override fun onInserted(position: Int, count: Int) {
        when {
            isLoading -> notifyDataSetChanged()
            else -> super.onInserted(position, count)
        }
    }
}
    */