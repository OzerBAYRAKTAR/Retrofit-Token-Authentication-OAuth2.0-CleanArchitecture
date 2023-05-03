package com.example.setsiscase.di


import android.content.Context
import com.example.setsiscase.data.remote.Authentication.AuthAuthenticator
import com.example.setsiscase.data.remote.Authentication.AuthInterceptor
import com.example.setsiscase.data.source.api.SetsisApi
import com.example.setsiscase.data.repository.api.SetsisRepositoryImp
import com.example.setsiscase.domain.repository.api.SetsisRepository
import com.example.setsiscase.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



        @Provides
        @Singleton
        fun providesRetrofit(@ApplicationContext context: Context): SetsisApi {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkHttpClient(context))
                .build()
                .create(SetsisApi::class.java)
        }

        @Singleton
        @Provides
        fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
            val intercepter= HttpLoggingInterceptor()
            intercepter.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .authenticator(AuthAuthenticator(context))
                .addInterceptor(AuthInterceptor(context))
                .addInterceptor(intercepter)
                .build()
        }

        @Provides
        @Singleton
        fun provideSetsisRepository(api: SetsisApi): SetsisRepository {
            return SetsisRepositoryImp(api)
        }

    }
