package com.vishaan.movieapp.utils.ext

import com.vishaan.movieapp.api.response.ErrorResponse
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import timber.log.Timber


fun <T : ResponseBody> T?.message(): String? {
    return this?.use {
        var message: String? = null
        try {
            val gson = GsonBuilder().create()
            val response = gson.fromJson(this.string(), ErrorResponse::class.java)
            message = response.error
        } catch (e: Exception) {
            Timber.e(e)
        }

        return@use message
    }
}