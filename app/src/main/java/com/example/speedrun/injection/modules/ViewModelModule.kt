package com.example.speedrun.injection.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speedrun.ui.game.GameDetailsViewModel
import com.example.speedrun.ui.main.MainViewModel
import com.example.speedrun.ui.run.RunDetailsViewModel
import com.example.speedrun.ui.splash.SplashViewModel
import com.example.speedrun.ui.user.UserProfileViewModel
import com.example.speedrun.viewmodel.SpeedrunViewModelFactory
import com.example.speedrun.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: SpeedrunViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel::class)
    fun bindUserProfileViewModel(viewModel: UserProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GameDetailsViewModel::class)
    fun bindGameDetailsViewModel(viewModel: GameDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RunDetailsViewModel::class)
    fun bindRunDetailsViewModel(viewModel: RunDetailsViewModel): ViewModel

}