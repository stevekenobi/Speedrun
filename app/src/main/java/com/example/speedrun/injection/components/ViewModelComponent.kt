package com.example.speedrun.injection.components

import com.example.speedrun.injection.PerViewModel
import com.example.speedrun.injection.modules.ViewModelModule
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
import dagger.Component


@PerViewModel
@Component(dependencies = [ApplicationComponent::class], modules = [ViewModelModule::class])
interface ViewModelComponent {
    fun inject(viewModelProvider: SpeedrunViewModelFactory)

    fun inject(viewModel: LatestRunsViewModel)
    fun inject(viewModel: PopularGamesViewModel)
    fun inject(viewModel: SeriesViewModel)
    fun inject(viewModel: SplashViewModel)
    fun inject(viewModel: UserRunsViewModel)
    fun inject(viewModel: UserMenuViewModel)
    fun inject(viewModel: GameDetailsViewModel)
    fun inject(viewModel: RunDetailsViewModel)
    fun inject(viewModel: LeaderboardViewModel)
    fun inject(viewModel: GameLeaderboardViewModel)
}