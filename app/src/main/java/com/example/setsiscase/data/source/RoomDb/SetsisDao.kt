package com.example.setsiscase.data.source.RoomDb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.setsiscase.data.remote.dto.Product
import com.example.setsiscase.domain.model.ProductModelUI


@Dao
interface SetsisDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(product: ProductModelUI)

    @Delete
    suspend fun delete(product: ProductModelUI)

    @Query("SELECT * FROM favorites")
    fun getAllProducts(): List<ProductModelUI>

    @Query("SELECT SUM(price) from favorites")
    fun getTotalCart():LiveData<Int>
}