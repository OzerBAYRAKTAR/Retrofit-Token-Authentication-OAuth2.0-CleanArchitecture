package com.example.setsiscase.data.remote

import android.content.Context
import com.example.setsiscase.util.SessionManager
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthInterceptor @Inject constructor(@ApplicationContext context: Context) : Interceptor {
    private val sessionManager= SessionManager(context)


    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // save token for get request
        val token=sessionManager.getToken()
        requestBuilder.addHeader("Authorization","Bearer $token")
        println(" auth $token")

        return chain.proceed(requestBuilder.build())
    }

}