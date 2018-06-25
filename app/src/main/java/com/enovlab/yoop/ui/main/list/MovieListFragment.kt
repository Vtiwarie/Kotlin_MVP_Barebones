package com.enovlab.yoop.ui.main.list

import android.os.Bundle
import android.support.v7.widget.SimpleItemAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
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

        movieListAdapter.listener = { navigator.navigateToMovieDetails.go(it.id) }
        movie_list.adapter = movieListAdapter

        search_button.setOnClickListener {
            viewModel.refresh(search_field.editableText.toString().trim())
        }
    }

    override fun submitList(movies: List<Movie>) {
        movieListAdapter.submitList(movies)
    }

    override fun showList(show: Boolean) {
        movie_list.isVisible = show
    }

    override fun showEmptyList(show: Boolean) {
        empty.isVisible = show
    }

    override fun showLoadingIndicator(active: Boolean) {
        movieListAdapter.isLoading = active
    }

    override fun showError(message: String?) {
        Toast.makeText(activity!!, message, Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance() = MovieListFragment()
    }
}