package com.example.setsiscase.domain.use_case.get_product


import com.example.setsiscase.domain.repository.SetsisRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val repository: SetsisRepository
) {
    suspend operator fun invoke(CategoryId: Int,
                                pageNumber: Int) = repository.getProductsByCategoryId(CategoryId,pageNumber)

}