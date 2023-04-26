package com.example.setsiscase.di

import android.content.Context
import com.example.setsiscase.data.remote.AuthInterceptor
import com.example.setsiscase.data.remote.SetsisApi
import com.example.setsiscase.data.repository.SetsisRepositoryImp
import com.example.setsiscase.domain.repository.SetsisRepository
import com.example.setsiscase.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun providesSetsisApi(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): SetsisApi {
        return retrofitBuilder.client(okHttpClient).build().create(SetsisApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSetsisRepository(api: SetsisApi): SetsisRepository {
        return SetsisRepositoryImp(api)
    }


}