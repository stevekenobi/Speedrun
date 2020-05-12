package com.example.speedrun.injection.components

import com.example.speedrun.injection.PerViewHolder
import com.example.speedrun.ui.game.LeaderboardRunPlayerViewHolder
import com.example.speedrun.ui.game.LeaderboardRunViewHolder
import com.example.speedrun.ui.game.LevelViewHolder
import com.example.speedrun.ui.main.LatestGameViewHolder
import com.example.speedrun.ui.main.LatestPlayersViewHolder
import com.example.speedrun.ui.main.LatestRunViewHolder
import com.example.speedrun.ui.user.UserGameViewHolder
import com.example.speedrun.ui.user.UserRunViewHolder
import dagger.Component

@PerViewHolder
@Component(dependencies = [ApplicationComponent::class])
interface ViewHolderComponent {
    fun inject(holder: LatestGameViewHolder)
    fun inject(holder: LatestRunViewHolder)
    fun inject(holder: LatestPlayersViewHolder)
    fun inject(holder: UserGameViewHolder)
    fun inject(holder: UserRunViewHolder)
    fun inject(holder: LeaderboardRunViewHolder)
    fun inject(holder: LeaderboardRunPlayerViewHolder)
    fun inject(holder: LevelViewHolder)
}