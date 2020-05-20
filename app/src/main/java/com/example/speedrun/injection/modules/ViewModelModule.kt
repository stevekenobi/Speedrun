package com.example.speedrun.injection.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speedrun.ui.game.details.GameDetailsViewModel
import com.example.speedrun.ui.game.leaderboard.GameLeaderboardViewModel
import com.example.speedrun.ui.game.leaderboard.LeaderboardViewModel
import com.example.speedrun.ui.main.latest.LatestRunsViewModel
import com.example.speedrun.ui.main.popular.PopularGamesViewModel
import com.example.speedrun.ui.main.series.SeriesViewModel
import com.example.speedrun.ui.run.RunDetailsViewModel
import com.example.speedrun.ui.splash.SplashViewModel
import com.example.speedrun.ui.user.drawer.UserMenuViewModel
import com.example.speedrun.ui.user.runs.UserRunsViewModel
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
    @ViewModelKey(LatestRunsViewModel::class)
    fun bindLatestRunsViewModel(viewModel: LatestRunsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PopularGamesViewModel::class)
    fun bindPopularGamesViewModel(viewModel: PopularGamesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SeriesViewModel::class)
    fun bindSeriesViewModel(viewModel: SeriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserRunsViewModel::class)
    fun bindUserRunsViewModel(viewModel: UserRunsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserMenuViewModel::class)
    fun bindUserMenuViewModel(viewModel: UserMenuViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GameDetailsViewModel::class)
    fun bindGameDetailsViewModel(viewModel: GameDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RunDetailsViewModel::class)
    fun bindRunDetailsViewModel(viewModel: RunDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LeaderboardViewModel::class)
    fun bindLeaderboardViewModel(viewModel: LeaderboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GameLeaderboardViewModel::class)
    fun bindGameLeaderBoardViewModel(viewModel: GameLeaderboardViewModel): ViewModel

}