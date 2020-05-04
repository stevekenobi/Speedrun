package com.example.speedrun.ui.game

import android.view.View
import com.example.network.model.dto.LeaderboardRunDto
import com.example.speedrun.ui.base.BaseViewHolder
import com.example.speedrun.utils.RunTimeConverter
import com.example.speedrun.utils.RunsTextUtils
import kotlinx.android.synthetic.main.item_leaderboard_run.view.*

class LeaderboardRunViewHolder(itemView: View) : BaseViewHolder(itemView) {

    init {
        viewHolderComponent()?.inject(this)
    }

    fun bind(run: LeaderboardRunDto) {
        itemView.apply {
            leaderboard_run_place.text = RunsTextUtils.setPlaceForLeaderboard(run.place)

            leaderboard_run_time.text = RunTimeConverter.from(run.run.times.primary_t)
        }
    }
}