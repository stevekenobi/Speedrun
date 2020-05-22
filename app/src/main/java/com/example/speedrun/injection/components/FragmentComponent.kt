package com.example.speedrun.injection.components

import android.content.Context
import com.example.speedrun.injection.context.ActivityContext
import com.example.speedrun.injection.modules.FragmentModule
import com.example.speedrun.injection.scopes.FragmentScope
import com.example.speedrun.ui.base.BaseFragment
import com.example.speedrun.ui.game.leaderboard.LeaderboardFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {
    @ActivityContext
    fun getContext(): Context

    fun inject(fragment: BaseFragment)

    fun inject(fragment: LeaderboardFragment)
}