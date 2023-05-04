package com.example.setsiscase.domain.use_case.api_use_case.get_product



import com.example.setsiscase.data.remote.dto.toProductModelUI
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.domain.repository.api.SetsisRepository
import com.example.setsiscase.util.ResourceUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetProductUseCase @Inject constructor(
    private val repository: SetsisRepository
) {

    suspend fun invoke(categoryId: Int, pageNumber: Int) = repository.getProductsByCategoryId(categoryId,pageNumber)
}