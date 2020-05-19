package com.example.speedrun.injection.components

import com.example.speedrun.injection.PerViewHolder
import com.example.speedrun.ui.game.leaderboard.LeaderboardRunPlayerViewHolder
import com.example.speedrun.ui.game.leaderboard.LeaderboardRunViewHolder
import com.example.speedrun.ui.main.latest.LatestGameViewHolder
import com.example.speedrun.ui.main.latest.LatestPlayersViewHolder
import com.example.speedrun.ui.main.latest.LatestRunViewHolder
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
}