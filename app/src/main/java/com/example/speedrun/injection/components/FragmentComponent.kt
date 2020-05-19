package com.example.speedrun.injection.components

import android.content.Context
import com.example.speedrun.injection.ActivityContext
import com.example.speedrun.injection.PerFragment
import com.example.speedrun.injection.modules.FragmentModule
import com.example.speedrun.ui.base.BaseFragment
import com.example.speedrun.ui.game.leaderboard.LeaderboardFragment
import dagger.Component

@PerFragment
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {
    @ActivityContext
    fun getContext(): Context

    fun inject(fragment: BaseFragment)

    fun inject(fragment: LeaderboardFragment)
}