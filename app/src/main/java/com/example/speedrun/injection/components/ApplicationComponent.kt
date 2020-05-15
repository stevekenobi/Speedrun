package com.example.speedrun.injection.components


import android.app.Application
import com.example.data.Datamanager
import com.example.data.injection.DataModule
import com.example.network.Session
import com.example.network.apis.SpeedrunService
import com.example.network.injection.NetworkModule
import com.example.speedrun.injection.modules.ApplicationModule
import com.example.speedrun.injection.modules.ConnectivityModule
import com.example.speedrun.injection.modules.EventBusModule
import com.example.speedrun.injection.modules.ViewModelModule
import com.example.speedrun.utils.Connectivity
import com.example.speedrun.utils.LifecycleListener
import com.example.speedrun.viewmodel.SpeedrunViewModelFactory
import com.example.storage.DatabaseSpeedrun
import com.example.storage.injection.StorageModule
import dagger.BindsInstance
import dagger.Component
import org.greenrobot.eventbus.EventBus
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ConnectivityModule::class,
        DataModule::class,
        EventBusModule::class,
        NetworkModule::class,
        StorageModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun session(): Session

    fun speedrunService(): SpeedrunService

    fun database(): DatabaseSpeedrun

    fun datamanager(): Datamanager

    fun application(): Application

    fun viewModelFactory(): SpeedrunViewModelFactory

    fun eventBus(): EventBus

    fun connectivity(): Connectivity

    fun inject(lifecycleListener: LifecycleListener)

    @Component.Builder
    interface Builder {

        fun build(): ApplicationComponent

        @BindsInstance
        fun applicationContext(applicationContext: Application): Builder
    }

}
