package com.example.setsiscase.domain.use_case.api_use_case.get_home


import com.example.setsiscase.data.remote.dto.toProductModelUI
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.domain.repository.api.SetsisRepository
import com.example.setsiscase.util.ResourceUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetHomeUseCase @Inject constructor(
    private val repository: SetsisRepository
) {
    operator fun invoke(): Flow<ResourceUtil<List<ProductModelUI>>> = flow {
        try {
            emit(ResourceUtil.Loading<List<ProductModelUI>>())
            val products = repository.getRandomProducts().products.map {
                it.toProductModelUI()
            }
            emit(ResourceUtil.Success<List<ProductModelUI>>(products))
        } catch(e: HttpException) {
            emit(ResourceUtil.Error<List<ProductModelUI>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(ResourceUtil.Error<List<ProductModelUI>>("Couldn't reach server. Check your internet connection."))
        }
    }
}