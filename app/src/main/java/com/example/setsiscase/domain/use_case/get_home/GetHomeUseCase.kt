package com.example.setsiscase.domain.use_case.get_home


import android.util.Log
import com.example.setsiscase.data.remote.dto.toProductModelUI
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.domain.repository.SetsisRepository
import com.example.setsiscase.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetHomeUseCase @Inject constructor(
    private val repository: SetsisRepository
) {
     operator fun invoke(): Flow<Resource<List<ProductModelUI>>> = flow{

    }

}