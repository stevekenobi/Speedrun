package com.example.speedrun.utils

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.speedrun.application.SpeedrunApplication
import org.greenrobot.eventbus.EventBus
import timber.log.Timber
import javax.inject.Inject

class LifecycleListener(application: Application) : LifecycleObserver {

    @Inject
    lateinit var eventBus: EventBus
    init {
        (application as SpeedrunApplication).component.inject(this)
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
