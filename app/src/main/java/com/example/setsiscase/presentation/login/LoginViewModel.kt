package com.example.setsiscase.presentation.login


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.setsiscase.data.remote.dto.LoginRequest
import com.example.setsiscase.data.remote.dto.LoginResponse
import com.example.setsiscase.domain.repository.SetsisRepository
import com.example.setsiscase.domain.use_case.get_login.GetLoginUseCase
import com.example.setsiscase.util.Resource
import com.example.setsiscase.util.SessionManager
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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
        val login=LoginRequest("testuser","123456")
        repository.login(login)
            .enqueue(object : Callback<LoginResponse>{
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