package com.enovlab.yoop.ui.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.enovlab.yoop.R
import com.enovlab.yoop.data.entity.Movie
import com.enovlab.yoop.ui.base.ViewModelOwner
import com.enovlab.yoop.ui.main.MainFragment
import com.enovlab.yoop.ui.main.list.adapter.MovieListAdapter
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : MainFragment<MovieListView, MovieListViewModel>(), MovieListView {
    override val viewModelOwner = ViewModelOwner.FRAGMENT
    override val vmClass = MovieListViewModel::class.java

    private val movieListAdapter: MovieListAdapter by lazy { MovieListAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieListAdapter.listener = {}
        movie_list.adapter = movieListAdapter
    }

    override fun submitList(movies: List<Movie>) {
        movieListAdapter.submitList(movies)
    }

    companion object {
        fun newInstance() = MovieListFragment()
    }
}