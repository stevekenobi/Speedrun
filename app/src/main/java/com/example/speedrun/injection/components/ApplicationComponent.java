package com.example.speedrun.injection.components;


import android.app.Application;

import com.example.data.Datamanager;
import com.example.data.injection.DataModule;
import com.example.network.Session;
import com.example.network.SpeedrunService;
import com.example.network.injection.NetworkModule;
import com.example.speedrun.injection.modules.ApplicationModule;
import com.example.speedrun.injection.modules.ConnectivityModule;
import com.example.speedrun.injection.modules.EventBusModule;
import com.example.speedrun.injection.modules.ViewModelModule;
import com.example.speedrun.utils.Connectivity;
import com.example.speedrun.utils.LifecycleListener;
import com.example.speedrun.viewmodel.SpeedrunViewModelFactory;
import com.example.storage.DatabaseSpeedrun;
import com.example.storage.injection.StorageModule;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        DataModule.class,
        ConnectivityModule.class,
        EventBusModule.class,
        NetworkModule.class,
        StorageModule.class,
        ViewModelModule.class
})
public interface ApplicationComponent {

    Session session();

    SpeedrunService speedrunService();

    DatabaseSpeedrun database();

    Datamanager datamanager();

    Application application();

    SpeedrunViewModelFactory viewModelFactory();

    EventBus eventBus();

    Connectivity connectivity();

    void inject(LifecycleListener lifecycleListener);

    @Component.Builder
    interface Builder {

        ApplicationComponent build();

        @BindsInstance
        Builder applicationContext(Application applicationContext);
    }

}
