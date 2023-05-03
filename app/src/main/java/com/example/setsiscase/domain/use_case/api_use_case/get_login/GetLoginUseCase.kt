package com.example.setsiscase.domain.use_case.api_use_case.get_login

import com.example.setsiscase.data.remote.Authentication.TokenManager
import com.example.setsiscase.data.remote.dto.LoginRequest
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.domain.repository.api.SetsisRepository
import com.example.setsiscase.util.ResourceUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.RequestBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(
    private val repository: SetsisRepository,
    private val tokenManager: TokenManager
) {
      fun invoke(request: LoginRequest) = repository.login(request)


    fun saveAccessTokens(accessToken: String, refreshToken: String) {
        tokenManager.saveToken(accessToken, refreshToken)
    }
}