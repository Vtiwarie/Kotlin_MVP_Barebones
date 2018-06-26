package com.vishaan.movieapp.api.serialize

import com.vishaan.movieapp.data.entity.Search
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class MovieDeserializer : JsonDeserializer<Search> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Search {
        val obj = json.asJsonObject.get("Search")

        val arr = obj.asJsonArray

        return Gson().fromJson(arr, typeOfT)
    }
}