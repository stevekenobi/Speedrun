package com.example.speedrun.injection.components

import com.example.speedrun.injection.PerViewModel
import com.example.speedrun.injection.modules.ViewModelModule
import com.example.speedrun.ui.game.GameDetailsViewModel
import com.example.speedrun.ui.game.LeaderboardViewModel
import com.example.speedrun.ui.main.MainViewModel
import com.example.speedrun.ui.run.RunDetailsViewModel
import com.example.speedrun.ui.splash.SplashViewModel
import com.example.speedrun.ui.user.UserProfileViewModel
import com.example.speedrun.viewmodel.SpeedrunViewModelFactory
import dagger.Component


@PerViewModel
@Component(dependencies = [ApplicationComponent::class], modules = [ViewModelModule::class])
interface ViewModelComponent {
    fun inject(viewModelProvider: SpeedrunViewModelFactory)

    fun inject(viewModel: MainViewModel)
    fun inject(viewModel: SplashViewModel)
    fun inject(viewModel: UserProfileViewModel)
    fun inject(viewModel: GameDetailsViewModel)
    fun inject(viewModel: RunDetailsViewModel)
    fun inject(viewModel: LeaderboardViewModel)
}