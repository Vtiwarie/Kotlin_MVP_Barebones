package com.vishaan.movieapp.ui.base.list

import android.view.ViewGroup
import androidx.core.view.isVisible
import com.vishaan.movieapp.R
import kotlinx.android.synthetic.main.item_infinite_loading.*

class LoadingViewHolder(parent: ViewGroup) : BaseViewHolder(parent, R.layout.item_infinite_loading) {

    fun bind() {
        loading.isVisible = true
    }
}