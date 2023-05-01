package com.example.setsiscase.presentation.login


import androidx.lifecycle.ViewModel
import com.example.setsiscase.data.remote.dto.LoginRequest
import com.example.setsiscase.data.remote.dto.LoginResponse
import com.example.setsiscase.domain.repository.api.SetsisRepository
import com.example.setsiscase.util.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: SetsisRepository, ): ViewModel() {

    @Inject
    lateinit var sessionManager: SessionManager


    fun getLogin() {
        val login= LoginRequest("testuser","123456")
        repository.login(login)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val loginResponse=response.body()
                    if (loginResponse?.token?.accessToken !=null) {
                        sessionManager.saveToken(loginResponse.token.accessToken)
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                }

            })

    }
}