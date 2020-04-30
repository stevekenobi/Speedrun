package com.example.speedrun.application;


import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ProcessLifecycleOwner;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.example.speedrun.R;
import com.example.speedrun.injection.components.ApplicationComponent;
import com.example.speedrun.injection.components.DaggerApplicationComponent;
import com.example.speedrun.utils.LifecycleListener;
import com.ice.restring.Restring;
import com.ice.restring.RestringConfig;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import timber.log.Timber;

public class SpeedrunApplication extends Application {

    private String TAG = SpeedrunApplication.class.getSimpleName();

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initTimber();
        initCalligraphy();
        initRestring();
        initLifeCycleListener();
        TypefaceProvider.registerDefaultIconSets();
    }

    private void initLifeCycleListener() {
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new LifecycleListener(this));
    }

    public static SpeedrunApplication get(Context context) {
        return (SpeedrunApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (applicationComponent == null) {
                applicationComponent =
                        DaggerApplicationComponent.builder()
                                .applicationContext(this)
                            .build();
        }

        return this.applicationComponent;
    }

    private void initTimber() {
            Timber.plant(new Timber.DebugTree());

    }

    private void initCalligraphy() {
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("font/roboto_slab_black.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }
    
    private void initRestring() {
        Restring.init(this,
                new RestringConfig.Builder()
                        .persist(false)
                        .build()
        );
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
