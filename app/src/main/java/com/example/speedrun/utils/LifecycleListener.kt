package com.example.speedrun.utils

import android.app.Application
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.speedrun.application.SpeedrunApplication
import timber.log.Timber

class LifecycleListener(application: Application) : LifecycleObserver {

    init {
        (application as SpeedrunApplication).getComponent().inject(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onMoveToForeground(): Unit {
        Timber.d("Returning to foreground…")
        //        eventBus.postSticky(new ReturningToForegroundEvent());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onMoveToBackground(): Unit {
        Timber.d("Moving to background…")
    }
}
