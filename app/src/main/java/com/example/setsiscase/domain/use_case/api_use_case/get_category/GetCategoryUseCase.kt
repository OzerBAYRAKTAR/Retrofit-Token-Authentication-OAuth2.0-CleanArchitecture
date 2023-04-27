package com.example.setsiscase.domain.use_case.api_use_case.get_category

import com.example.setsiscase.domain.model.CategoryModelUI
import com.example.setsiscase.domain.repository.api.SetsisRepository
import com.example.setsiscase.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val repository: SetsisRepository
) {
    operator fun invoke(): Flow<Resource<List<CategoryModelUI>>> = flow {
        try {
            emit(Resource.Loading<List<CategoryModelUI>>())
            val categories = repository.getAllCategories().categories.map {
                it.toCategoryModelUI()
            }
            emit(Resource.Success<List<CategoryModelUI>>(categories))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<CategoryModelUI>>(
                    e.localizedMessage ?: "An unexpected error occured"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<List<CategoryModelUI>>("Couldn't reach server. Check your internet connection."))
        }
    }
}