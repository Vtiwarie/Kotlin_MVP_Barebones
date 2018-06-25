package com.enovlab.yoop.ui.main.list.adapter

import android.view.ViewGroup
import com.enovlab.yoop.R
import com.enovlab.yoop.data.entity.Movie
import com.enovlab.yoop.ui.base.list.BaseViewHolder
import com.enovlab.yoop.utils.ext.inflateView
import com.enovlab.yoop.utils.ext.loadImage
import kotlinx.android.synthetic.main.item_movie.*

class MovieListViewHolder(parent: ViewGroup, val listener: ((Movie) -> Unit)? = null)
    : BaseViewHolder(inflateView(R.layout.item_movie, parent)) {

    fun bind(movie: Movie) {
        header.text = movie.title
        when {
            movie.defaultImage == null -> list_image.setImageResource(android.R.drawable.ic_menu_close_clear_cancel)
            else -> list_image.loadImage(movie.defaultImage)
        }
    }
}
