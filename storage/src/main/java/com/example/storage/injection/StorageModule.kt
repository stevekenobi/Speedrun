package com.example.storage.injection

import android.app.Application
import androidx.room.Room
import com.example.storage.DatabaseSpeedrun
import com.example.storage.SharedPreferencesManager
import com.example.storage.interfaces.AuthorizationStorageManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {
    @Provides
    @Singleton
    fun provideSharedPreferencesManager(application: Application): SharedPreferencesManager {
        return SharedPreferencesManager(application)
    }

    @Provides
    @Singleton
    fun provideAuthorizationStorageManager(
        sharedPreferencesManager: SharedPreferencesManager): AuthorizationStorageManager {
        return sharedPreferencesManager
    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application): DatabaseSpeedrun {
        return Room.databaseBuilder(
            application,
            DatabaseSpeedrun::class.java,
            DatabaseSpeedrun::class.java.name)
            .fallbackToDestructiveMigration()
            .build()
    }
}