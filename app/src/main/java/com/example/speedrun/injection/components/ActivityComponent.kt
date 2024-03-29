package com.example.speedrun.injection.components

import com.example.speedrun.injection.modules.ActivityModule
import com.example.speedrun.injection.scopes.ActivityScope
import com.example.speedrun.ui.base.BaseActivity
import com.example.speedrun.ui.game.GameDetailsActivity
import com.example.speedrun.ui.main.MainActivity
import com.example.speedrun.ui.run.RunDetailsActivity
import com.example.speedrun.ui.splash.SplashActivity
import com.example.speedrun.ui.user.UserProfileActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: BaseActivity)

    fun inject(activity: MainActivity)
    fun inject(activity: SplashActivity)
    fun inject(activity: UserProfileActivity)
    fun inject(activity: GameDetailsActivity)
    fun inject(activity: RunDetailsActivity)
    //Context activityContext();
}