package com.example.setsiscase.domain.use_case.api_use_case.get_login

import com.example.setsiscase.data.remote.dto.LoginRequest
import com.example.setsiscase.domain.repository.api.SetsisRepository
import okhttp3.RequestBody
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(
    private val repository: SetsisRepository
) {
     operator suspend fun invoke(request: LoginRequest) = repository.login(request)

}