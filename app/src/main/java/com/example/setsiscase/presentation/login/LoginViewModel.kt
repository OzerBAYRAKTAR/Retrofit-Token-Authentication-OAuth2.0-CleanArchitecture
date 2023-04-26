package com.example.setsiscase.presentation.login


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.setsiscase.data.remote.dto.LoginRequest
import com.example.setsiscase.data.remote.dto.LoginResponse
import com.example.setsiscase.domain.use_case.get_login.GetLoginUseCase
import com.example.setsiscase.util.Resource
import com.example.setsiscase.util.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getLoginUseCase: GetLoginUseCase): ViewModel() {

    @Inject
    lateinit var sessionManager: SessionManager

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

          fun login(username: String, password: String){
            val login =LoginRequest(username,password)
            getLoginUseCase.invoke(login).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val loginResponse= response.body()
                    if (loginResponse?.token != null) {
                        sessionManager.saveToken(loginResponse.token.accessToken)
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                }

            })
        }
}