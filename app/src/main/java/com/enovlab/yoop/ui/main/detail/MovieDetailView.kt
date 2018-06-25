package com.enovlab.yoop.ui.main.detail

import com.enovlab.yoop.ui.base.state.StateView

interface MovieDetailView : StateView {
    fun showNoImage()
    fun showMovieImage(url: String?)
    fun showMovieTitle(title: String?)
    fun showMoviePlot(plot: String?)
}