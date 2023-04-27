package com.example.setsiscase.domain.use_case.get_product


import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.domain.repository.SetsisRepository
import com.example.setsiscase.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val repository: SetsisRepository
) {

    operator fun invoke(categoryId: Int): Flow<Resource<List<ProductModelUI>>> = flow {
        try {
            emit(Resource.Loading<List<ProductModelUI>>())
            val products = repository.getProductsByCategoryId(categoryId).products.map {
                it.toProductModelUI()
            }
            emit(Resource.Success<List<ProductModelUI>>(products))
        } catch(e: HttpException) {
            emit(Resource.Error<List<ProductModelUI>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<ProductModelUI>>("Couldn't reach server. Check your internet connection."))
        }
    }
}