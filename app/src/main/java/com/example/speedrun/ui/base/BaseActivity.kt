package com.example.speedrun.ui.base

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.data.Datamanager
import com.example.speedrun.application.SpeedrunApplication
import com.example.speedrun.injection.components.ActivityComponent
import com.example.speedrun.injection.components.ApplicationComponent
import com.example.speedrun.injection.components.DaggerActivityComponent
import com.example.speedrun.injection.modules.ActivityModule
import com.ice.restring.Restring
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import timber.log.Timber
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var dataManager: Datamanager

    private var activityComponent: ActivityComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        if (activityComponent == null) {
            val applicationComponent: ApplicationComponent =
                (application as SpeedrunApplication).component
            activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(ActivityModule(this))
                .build()

            activityComponent?.inject(this)
        }
    }

    override fun attachBaseContext(newBase: Context?) {
//        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
        Timber.d("attachBaseContext")
        super.attachBaseContext(ViewPumpContextWrapper.wrap(Restring.wrapContext(newBase)))
    }

    open fun displayToast(message: CharSequence?, duration: Int) {
        Toast.makeText(this, message, duration).show()
    }

    open fun displayToast(@StringRes message: Int, duration: Int) {
        Toast.makeText(this, message, duration).show()
    }
}