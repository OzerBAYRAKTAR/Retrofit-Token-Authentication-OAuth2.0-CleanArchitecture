package com.example.setsiscase.domain.use_case.room_use_case

import androidx.lifecycle.LiveData
import com.example.setsiscase.domain.model.InvalidFavoriteException
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.domain.repository.room.SetsisRoomRepository
import com.example.setsiscase.util.ResourceUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetAllCart(
    private val repository: SetsisRoomRepository
) {
    @Throws(InvalidFavoriteException::class)
    operator fun invoke(): Flow<ResourceUtil<List<ProductModelUI>>> = flow {
        try {
            emit(ResourceUtil.Loading<List<ProductModelUI>>())
            val categories = repository.getAllProducts()
            emit(ResourceUtil.Success<List<ProductModelUI>>(categories))
        } catch (e: HttpException) {
            emit(
                ResourceUtil.Error<List<ProductModelUI>>(
                    e.localizedMessage ?: "An unexpected error occured"
                )
            )
        } catch (e: IOException) {
            emit(ResourceUtil.Error<List<ProductModelUI>>("Couldn't reach server. Check your internet connection."))
        }
    }
}