package com.vishaan.movieapp.ui.main.list

import com.vishaan.movieapp.data.entity.Movie
import com.vishaan.movieapp.ui.base.state.StateView

interface MovieListView : StateView {
    fun submitList(movies: List<Movie>)
    fun showEmptyList(show: Boolean)
    fun showList(show: Boolean)
}