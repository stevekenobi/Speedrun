package com.example.speedrun.injection.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speedrun.ui.base.BaseViewModel
import com.example.speedrun.ui.main.MainViewModel
import com.example.speedrun.ui.splash.SplashViewModel
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
    fun bindMainViewModel(viewModel : MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(viewModel : SplashViewModel) : ViewModel

}