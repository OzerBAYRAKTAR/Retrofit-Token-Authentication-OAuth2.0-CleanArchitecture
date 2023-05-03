package com.example.setsiscase.presentation.login



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.setsiscase.data.remote.Authentication.TokenManager
import com.example.setsiscase.data.remote.dto.LoginRequest
import com.example.setsiscase.data.remote.dto.LoginResponse
import com.example.setsiscase.data.source.api.Resource
import com.example.setsiscase.domain.repository.api.SetsisRepository
import com.example.setsiscase.domain.use_case.api_use_case.get_login.GetLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: GetLoginUseCase): ViewModel() {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    @Inject
    lateinit var tokenManager: TokenManager


    fun getLogin() {
        val login= LoginRequest("testuser","123456")
        loginUseCase.invoke(login)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val loginResponse=response.body()
                    if (loginResponse?.token?.accessToken !=null) {
                        tokenManager.saveToken(
                            loginResponse.token.accessToken,
                            loginResponse.token.refreshToken)
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                }

            })

    }
     fun saveAccessTokens(accessToken: String, refreshToken: String) {
        loginUseCase.saveAccessTokens(accessToken, refreshToken)
    }
}
