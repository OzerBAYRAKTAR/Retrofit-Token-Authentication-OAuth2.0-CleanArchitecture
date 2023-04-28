package com.example.setsiscase.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.setsiscase.data.remote.AuthAuthenticator
import com.example.setsiscase.data.remote.AuthInterceptor
import com.example.setsiscase.data.source.api.SetsisApi
import com.example.setsiscase.data.repository.api.SetsisRepositoryImp
import com.example.setsiscase.data.repository.room.SetsisRoomRepositoryImp
import com.example.setsiscase.data.source.RoomDb.SetsisDatabase
import com.example.setsiscase.domain.repository.api.SetsisRepository
import com.example.setsiscase.domain.repository.room.SetsisRoomRepository
import com.example.setsiscase.domain.use_case.room_use_case.AddCart
import com.example.setsiscase.domain.use_case.room_use_case.DeleteCart
import com.example.setsiscase.domain.use_case.room_use_case.GetAllCart
import com.example.setsiscase.domain.use_case.room_use_case.RoomUseCases
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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): SetsisApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(SetsisApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor,authAuthenticator: AuthAuthenticator): OkHttpClient {
        val intercepter= HttpLoggingInterceptor()
        intercepter.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(intercepter)
            .authenticator(authAuthenticator)
            .build()
    }

    @Provides
    @Singleton
    fun provideSetsisRepository(api: SetsisApi): SetsisRepository {
        return SetsisRepositoryImp(api)
    }

    @Provides
    @Singleton
    fun provideSetsisRoomDatabase(app: Application): SetsisDatabase {
        return Room.databaseBuilder(
            app,
            SetsisDatabase::class.java,
            SetsisDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideSetsisRoomRepository(db: SetsisDatabase): SetsisRoomRepository {
        return SetsisRoomRepositoryImp(db.setsisDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: SetsisRoomRepository): RoomUseCases {
        return RoomUseCases(
            addCart = AddCart(repository),
            deleteCart = DeleteCart(repository),
            getAllCart = GetAllCart(repository)
        )
    }


}