package com.example.data.injection

import com.example.data.AuthSession
import com.example.data.CommonDataManager
import com.example.data.DataManager
import com.example.data.speedrun.AndroidSpeedrunDataManager
import com.example.data.speedrun.SpeedrunDataManager
import com.example.data.splits.AndroidSplitsDataManager
import com.example.data.splits.SplitsDataManager
import com.example.network.Session
import com.example.network.api.SpeedrunService
import com.example.network.api.SplitsService
import com.example.network.injection.NetworkModule
import com.example.storage.interfaces.AuthorizationStorageManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class DataModule {
    @Provides
    @Singleton
    fun provideSession(authorizationStorageManager: AuthorizationStorageManager): Session {
        return AuthSession(authorizationStorageManager)
    }

    @Provides
    @Singleton
    fun provideSpeedrunDataManager(speedrunService: SpeedrunService): SpeedrunDataManager {
        return AndroidSpeedrunDataManager(speedrunService)
    }

    @Provides
    @Singleton
    fun provideSplitsDataManager(splitsService: SplitsService): SplitsDataManager {
        return AndroidSplitsDataManager(splitsService)
    }

    @Provides
    @Singleton
    fun provideDataManager(splitsDataManager: SplitsDataManager, speedrunDataManager: SpeedrunDataManager): DataManager {
        return CommonDataManager(splitsDataManager, speedrunDataManager)
    }
}