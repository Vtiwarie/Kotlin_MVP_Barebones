package com.vishaan.movieapp.ui.main

import android.arch.lifecycle.ViewModel
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MainNavigator @Inject constructor() : ViewModel() {
    internal var navigateToMovieList = PublishSubject.create<Unit>()
    internal var navigateToMovieDetails = PublishSubject.create<String?>()
}