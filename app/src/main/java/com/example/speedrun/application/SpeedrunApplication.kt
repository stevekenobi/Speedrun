package com.example.speedrun.application

import android.app.Application
import android.content.Context
import androidx.lifecycle.ProcessLifecycleOwner
import com.beardedhen.androidbootstrap.TypefaceProvider
import com.example.speedrun.R
import com.example.speedrun.injection.components.ApplicationComponent
import com.example.speedrun.injection.components.DaggerApplicationComponent
import com.example.speedrun.utils.LifecycleListener
import com.ice.restring.Restring
import com.ice.restring.RestringConfig
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import timber.log.Timber

class SpeedrunApplication : Application() {

//    private val TAG = javaClass.simpleName

    private var applicationComponent: ApplicationComponent? = null


    override fun onCreate() {
        super.onCreate()

        initTimber()
        initCalligraphy()
        initRestring()
        initLifeCycleListener()
        TypefaceProvider.registerDefaultIconSets()
    }

    private fun initLifeCycleListener() {
        ProcessLifecycleOwner.get().lifecycle.addObserver(LifecycleListener(this))
    }

    fun getComponent(): ApplicationComponent? {
        if (applicationComponent == null) {
            applicationComponent =
                DaggerApplicationComponent.builder()
                    .applicationContext(this)
                    .build()
        }

        return this.applicationComponent
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())

    }

    private fun initCalligraphy() {
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/RobotoSlabThin.ttf")
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        )
    }

    private fun initRestring() {
        Restring.init(
            this, RestringConfig.Builder()
                .persist(false)
                .build()
        )
    }

    companion object {

        fun get(context: Context): SpeedrunApplication {
            return context.applicationContext as SpeedrunApplication
        }
    }

}
