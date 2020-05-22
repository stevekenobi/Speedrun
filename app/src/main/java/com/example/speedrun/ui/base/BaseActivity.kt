package com.example.speedrun.ui.base

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.speedrun.application.SpeedrunApplication
import com.example.speedrun.events.NetworkAvailableEvent
import com.example.speedrun.injection.components.ActivityComponent
import com.example.speedrun.injection.components.DaggerActivityComponent
import com.example.speedrun.injection.modules.ActivityModule
import com.example.speedrun.utils.Connectivity
import com.example.speedrun.viewmodel.SpeedrunViewModelFactory
import com.ice.restring.Restring
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var eventBus: EventBus

    @Inject
    lateinit var connectivity: Connectivity

    @Inject
    protected lateinit var viewModelFactory: SpeedrunViewModelFactory

    var activityComponent: ActivityComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        if (activityComponent == null) {
            val applicationComponent = (application as SpeedrunApplication).getComponent()
            activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(ActivityModule(this))
                .build()

            activityComponent?.inject(this)

            initViewModel()
            observeViewModel()
        }
    }

    abstract fun initViewModel()
    abstract fun observeViewModel()

    override fun onResume() {
        super.onResume()
        if (shouldListenToNetworkChanges()) {
            connectivity.startNetworkMonitoring()
        }

        eventBus.register(this)
    }

    override fun onPause() {
        super.onPause()

        connectivity.stopNetworkMonitoring()
        eventBus.unregister(this)
    }

    override fun attachBaseContext(newBase: Context?) {
//        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
        Timber.d("attachBaseContext")
        super.attachBaseContext(ViewPumpContextWrapper.wrap(Restring.wrapContext(newBase)))
    }

    fun buildAlertDialog(
        activity: BaseActivity,
        titleRes: Int,
        messageRes: Int,
        positiveRes: Int,
        positiveClick: DialogInterface.OnClickListener,
        negativeRes: Int,
        negativeClick: DialogInterface.OnClickListener,
        cancelable: Boolean
    ): android.app.AlertDialog {
        return BaseAlertDialog.builder(
            activity,
            titleRes,
            messageRes,
            positiveRes,
            positiveClick,
            negativeRes,
            negativeClick,
            cancelable
        )
    }

    open fun displayToast(message: CharSequence?, duration: Int) {
        Toast.makeText(this, message, duration).show()
    }

    open fun displayToast(@StringRes message: Int, duration: Int) {
        Toast.makeText(this, message, duration).show()
    }

    open fun shouldListenToNetworkChanges(): Boolean {
        return false
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onConnectionLost(networkAvailableEvent: NetworkAvailableEvent) {
        displayToast("No Network Available", Toast.LENGTH_LONG)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onConnectionAvailable(networkAvailableEvent: NetworkAvailableEvent) {
        displayToast("Network Back", Toast.LENGTH_LONG)
    }
}