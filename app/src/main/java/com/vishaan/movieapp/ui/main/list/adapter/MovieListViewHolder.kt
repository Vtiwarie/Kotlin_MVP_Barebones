package com.vishaan.movieapp.ui.main.list.adapter

import android.view.ViewGroup
import com.vishaan.movieapp.R
import com.vishaan.movieapp.data.entity.Movie
import com.vishaan.movieapp.ui.base.list.BaseViewHolder
import com.vishaan.movieapp.utils.ext.inflateView
import com.vishaan.movieapp.utils.ext.loadImage
import kotlinx.android.synthetic.main.item_movie.*

class MovieListViewHolder(parent: ViewGroup, val listener: ((Movie) -> Unit)? = null)
    : BaseViewHolder(inflateView(R.layout.item_movie, parent)) {

    fun bind(movie: Movie) {
        header.text = movie.title
        when {
            movie.defaultImage == null -> list_image.setImageResource(android.R.drawable.ic_menu_close_clear_cancel)
            else -> list_image.loadImage(movie.defaultImage)
        }

        if (listener != null) {
            itemView.setOnClickListener { listener.invoke(movie) }
        }
    }
}
