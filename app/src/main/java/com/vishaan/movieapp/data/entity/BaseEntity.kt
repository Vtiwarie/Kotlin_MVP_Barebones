package com.vishaan.movieapp.data.entity

import com.google.gson.annotations.SerializedName

open class BaseEntity {
    @SerializedName("Response")
    open var response: String? = null

    @SerializedName("Error")
    open var error: String? = null
}