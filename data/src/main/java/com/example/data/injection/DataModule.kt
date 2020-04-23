package com.example.data.injection

import com.example.data.AuthSession
import com.example.network.Session
import com.example.storage.interfaces.AuthorizationStorageManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideSession(authorizationStorageManager: AuthorizationStorageManager): Session {
        return AuthSession(authorizationStorageManager)
    }
}