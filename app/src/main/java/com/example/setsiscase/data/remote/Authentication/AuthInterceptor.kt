package com.example.setsiscase.data.remote.Authentication

import android.content.Context

import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.*
import javax.inject.Inject


class AuthInterceptor @Inject constructor(@ApplicationContext context: Context) : Interceptor {
    private val tokenManager= TokenManager(context)


    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // save token for get request
        val token=tokenManager.getToken()

        requestBuilder
            .addHeader("Authorization","Bearer $token")
        println(" auth $token")


        return chain.proceed(requestBuilder.build())
    }

}