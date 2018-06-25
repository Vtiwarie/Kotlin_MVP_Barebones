package com.enovlab.yoop.ui.main.list

import com.enovlab.yoop.data.entity.Movie
import com.enovlab.yoop.ui.base.state.StateView

interface MovieListView : StateView {
    fun submitList(movies: List<Movie>)
}