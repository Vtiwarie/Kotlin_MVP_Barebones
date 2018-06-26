package com.vishaan.movieapp.ui.base.list

interface ListItem {

    data class Typed(val type: Type) : ListItem

    enum class Type {
        LOADING, MOVIELIST
    }
}

