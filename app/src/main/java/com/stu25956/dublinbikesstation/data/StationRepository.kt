package com.stu25956.dublinbikesstation.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

// Repository for loading stations from the JSON file
object StationRepository {

    // Load stations from the JSON file
    fun loadStations(context: Context): List<Station> {
        // Open the JSON file
        val inputStream = context.assets.open("dublinbike.json")
        val reader = InputStreamReader(inputStream)
        val type = object : TypeToken<List<Station>>() {}.type
        return Gson().fromJson(reader, type)
    }
}