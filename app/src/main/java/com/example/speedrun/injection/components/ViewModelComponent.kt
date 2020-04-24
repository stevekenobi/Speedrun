package com.example.speedrun.injection.components

import com.example.speedrun.injection.PerViewModel
import com.example.speedrun.injection.modules.ViewModelModule
import com.example.speedrun.ui.main.MainViewModel
import com.example.speedrun.ui.splash.SplashViewModel
import com.example.speedrun.viewmodel.SpeedrunViewModelFactory
import dagger.Component


@PerViewModel
@Component(dependencies = [ApplicationComponent::class], modules = [ViewModelModule::class])
interface ViewModelComponent {
    fun inject(viewModelProvider : SpeedrunViewModelFactory)

    fun inject(viewModel: MainViewModel)
    fun inject(viewModel: SplashViewModel)
}