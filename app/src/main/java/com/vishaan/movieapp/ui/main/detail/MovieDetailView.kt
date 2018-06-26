package com.vishaan.movieapp.ui.main.detail

import com.vishaan.movieapp.ui.base.state.StateView

interface MovieDetailView : StateView {
    fun showNoImage()
    fun showMovieImage(url: String?)
    fun showMovieTitle(title: String?)
    fun showMoviePlot(plot: String?)
}