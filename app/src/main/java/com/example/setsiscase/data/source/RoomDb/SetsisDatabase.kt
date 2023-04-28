package com.example.setsiscase.data.source.RoomDb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.setsiscase.data.remote.dto.Product
import com.example.setsiscase.domain.model.ProductModelUI


@Database(entities = [ProductModelUI::class], version = 2)
@TypeConverters(TypeConverter::class)
abstract class SetsisDatabase: RoomDatabase() {

    abstract val setsisDao: SetsisDao

    companion object{
        const val DATABASE_NAME= "setsis_db"
    }
}