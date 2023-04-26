package com.example.setsiscase.domain.use_case.get_category

import com.example.setsiscase.domain.repository.SetsisRepository
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val repository: SetsisRepository
) {
    suspend operator fun invoke() = repository.getAllCategories()

}