package com.example.speedrun.injection.modules

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.speedrun.utils.Connectivity
import dagger.Module
import dagger.Provides
import org.greenrobot.eventbus.EventBus

@Module
class ConnectivityModule {

    //TODO Find what is wrong with providing
    @Provides
    fun providesConnectivity(application: Application, eventBus: EventBus): Connectivity {
        val connectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return Connectivity(connectivityManager, eventBus);
    }
}