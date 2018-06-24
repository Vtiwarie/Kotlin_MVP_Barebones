package com.enovlab.yoop.ui.main.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.enovlab.yoop.R
import com.enovlab.yoop.ui.base.ViewModelOwner
import com.enovlab.yoop.ui.main.MainFragment

class MovieDetailFragment : MainFragment<MovieDetailView, MovieDetailViewModel>(), MovieDetailView {
    override val viewModelOwner = ViewModelOwner.FRAGMENT
    override val vmClass = MovieDetailViewModel::class.java

//    private val adapter: MovieListAdapter by lazy { MovieListAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        internal const val PARAM_MOVIE_ID = "param-movie-id"

        fun newInstance(movieID: Int) = MovieDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(PARAM_MOVIE_ID, movieID)
            }
        }
    }
}