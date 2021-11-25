package com.sogefi.position.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class DoubleListTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromDoubleList(value: List<Double>?): String {
        if (value == null) return gson.toJson(emptyList<String>())
        return gson.toJson(value)
    }

    @TypeConverter
    fun toDoubleList(data: String?): List<Double>? {
        if (data == null) {
            return Collections.emptyList()
        }

        val mapType = object : TypeToken<List<Double>>() {}.type

        return gson.fromJson(data, mapType)
    }
}