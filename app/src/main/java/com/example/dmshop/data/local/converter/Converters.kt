package com.example.dmshop.data.local.converter

import androidx.room.TypeConverter
import com.example.dmshop.data.local.entity.CartItemEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromCartItemList(value: List<CartItemEntity>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toCartItemList(value: String): List<CartItemEntity> {
        val listType = object : TypeToken<List<CartItemEntity>>() {}.type
        return gson.fromJson(value, listType)
    }
} 