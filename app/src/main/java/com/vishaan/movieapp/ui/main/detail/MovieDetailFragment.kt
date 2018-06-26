package com.vishaan.movieapp.ui.main.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vishaan.movieapp.R
import com.vishaan.movieapp.ui.base.ViewModelOwner
import com.vishaan.movieapp.ui.main.MainFragment
import com.vishaan.movieapp.utils.ext.loadImage
import kotlinx.android.synthetic.main.fragment_detail_list.*

class MovieDetailFragment : MainFragment<MovieDetailView, MovieDetailViewModel>(), MovieDetailView {
    override val viewModelOwner = ViewModelOwner.FRAGMENT
    override val vmClass = MovieDetailViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.id = arguments?.getString(PARAM_MOVIE_ID, "")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_list, container, false)
    }

    override fun showMovieImage(url: String?) {
        movie_image.loadImage(url)
    }

    override fun showMovieTitle(title: String?) {
        movie_title.text = title
    }

    override fun showMoviePlot(plot: String?) {
        movie_plot.text = plot
    }

    override fun showNoImage() {
        movie_image.setImageResource(R.mipmap.ic_default_movie)
    }

    companion object {
        internal const val PARAM_MOVIE_ID = "param-movie-id"

        fun newInstance(movieID: String?) = MovieDetailFragment().apply {
            arguments = Bundle().apply {
                putString(PARAM_MOVIE_ID, movieID)
            }
        }
    }

}