package com.example.setsiscase.data.source.RoomDb

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class TypeConverter {

    @SuppressLint("SuspiciousIndentation")
    @TypeConverter
    fun fromAnyToString(attribute: Any?): String {
        if (attribute == null)
            return  ""
        return attribute as String
    }

    @TypeConverter
    fun fromStringToAny(attribute: String?): Any {
        if (attribute == null)
            return  ""
        return attribute
    }
}