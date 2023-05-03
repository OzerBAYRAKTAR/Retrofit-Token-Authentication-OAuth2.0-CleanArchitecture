package com.example.setsiscase.data.remote.Authentication

import android.content.Context
import com.example.setsiscase.data.remote.dto.LoginResponse
import com.example.setsiscase.data.remote.dto.TokenResponse
import com.example.setsiscase.data.repository.api.SafeApiCall
import com.example.setsiscase.data.source.api.Resource
import com.example.setsiscase.data.source.api.TokenRefreshApi
import com.example.setsiscase.util.Constants.BASE_URL
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class AuthAuthenticator @Inject constructor(
@ApplicationContext context: Context
) : Authenticator,SafeApiCall {

      private var tokenManager= TokenManager(context)

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking {
            tokenManager.getRefreshToken()
        }
        return runBlocking {

            val newToken = getUpdatedToken(token)
            println("newtoken1 = ${newToken.body()}")
            println("newtoken2 = ${token}")
            println("newtoken3 = ${tokenManager.getToken()}")
            println("newtoken4 = ${newToken.body()?.token?.accessToken}")
            println("newtoken5 = ${newToken.body()?.token?.refreshToken}")

            if (!newToken.isSuccessful || newToken.body() == null) { //Couldn't refresh the token, so restart the login process
                tokenManager.clearSharedPref()
                println("null auther")
            }

            newToken.body()?.let {
                println("body not null")
                tokenManager.saveToken(it.token.accessToken,it.token.refreshToken)
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.token.accessToken}")
                    .build()
            }
        }
    }

    private suspend fun getUpdatedToken(refreshToken: String?): retrofit2.Response<LoginResponse> {
        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val service = retrofit.create(TokenRefreshApi::class.java)
        val refreshBB=TokenResponse("$refreshToken")
        return  service.refreshAccessToken(refreshBB)
    }

}