package com.example.speedrun.injection.components;

import com.example.speedrun.ui.main.MainActivity;
import com.example.speedrun.injection.PerActivity;
import com.example.speedrun.injection.modules.ActivityModule;
import com.example.speedrun.ui.base.BaseActivity;
import com.example.speedrun.ui.splash.SplashActivity;

import dagger.Component;

@PerActivity
@Component(
        dependencies = {ApplicationComponent.class},
        modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(BaseActivity activity);

    void inject(MainActivity activity);
    void inject(SplashActivity activity);

    //Context activityContext();
}
