package com.enovlab.yoop.ui.main.list

import android.arch.lifecycle.ViewModel
import com.enovlab.yoop.inject.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MovieListBuilder {

    @ContributesAndroidInjector
    internal abstract fun contributeMovieListFragment(): MovieListFragment

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    internal abstract fun bindMovieListViewModel(viewModel: MovieListViewModel): ViewModel
}