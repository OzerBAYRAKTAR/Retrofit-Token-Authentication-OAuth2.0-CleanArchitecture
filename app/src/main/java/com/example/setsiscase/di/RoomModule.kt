package com.example.setsiscase.di

import android.app.Application
import androidx.room.Room
import com.example.setsiscase.data.repository.room.SetsisRoomRepositoryImp
import com.example.setsiscase.data.source.RoomDb.SetsisDatabase
import com.example.setsiscase.domain.repository.room.SetsisRoomRepository
import com.example.setsiscase.domain.use_case.room_use_case.AddCart
import com.example.setsiscase.domain.use_case.room_use_case.DeleteCart
import com.example.setsiscase.domain.use_case.room_use_case.GetAllCart
import com.example.setsiscase.domain.use_case.room_use_case.RoomUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    //local database
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