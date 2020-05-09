package com.example.speedrun.injection.modules


import android.app.Activity
import android.content.Context

import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun provideContext(): Context {
        return activity
    }

}
