package com.example.setsiscase.domain.use_case.get_home

import com.example.setsiscase.data.mapper.toProductModelUI
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.domain.repository.SetsisRepository
import com.example.setsiscase.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHomeUseCase @Inject constructor(
    private val repository: SetsisRepository
) {
    suspend operator fun invoke(): Flow<Resource<ProductModelUI>> = flow{

    }

}