package com.example.setsiscase.data.remote

import android.content.Context
import android.content.Intent
import com.example.setsiscase.data.remote.dto.LoginResponse
import com.example.setsiscase.data.source.api.SetsisApi
import com.example.setsiscase.presentation.login.LoginActivity
import com.example.setsiscase.util.Constants
import com.example.setsiscase.util.SessionManager
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(): Authenticator{
    @Inject lateinit var sessionManager: SessionManager


    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking {
            sessionManager.getToken()
        }
        return runBlocking {
            val newToken = getNewToken(token)

            if (!newToken.isSuccessful || newToken.body() == null) { //Couldn't refresh the token, so restart the login process
                sessionManager.deleteToken()
            }

            newToken.body()?.let {
                sessionManager.saveToken(token!!)
                response.request.newBuilder()
                    .header("Authorization","Bearer $token")
                    .build()
            }
        }

    }

    private suspend fun getNewToken(refreshToken: String?): retrofit2.Response<LoginResponse> {


        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(SetsisApi::class.java)
        return service.refreshAccessToken("Bearer $refreshToken")

    }
}