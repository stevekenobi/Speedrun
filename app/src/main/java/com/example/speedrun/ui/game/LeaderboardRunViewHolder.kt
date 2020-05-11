package com.example.speedrun.ui.game

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.network.model.dto.LeaderboardRunDto
import com.example.speedrun.ui.base.BaseViewHolder
import com.example.speedrun.utils.RunTimeConverter
import com.example.speedrun.utils.RunsTextUtils
import kotlinx.android.synthetic.main.item_leaderboard_run.view.*

class LeaderboardRunViewHolder(val viewModel: LeaderboardViewModel?, itemView: View) : BaseViewHolder(itemView) {

    init {
        viewHolderComponent()?.inject(this)
    }

    fun bind(run: LeaderboardRunDto) {
        itemView.apply {
            setOnClickListener {
                viewModel?.leaderboardRunClickedLiveData?.value = run.run.id
            }
            leaderboard_run_players.layoutManager = LinearLayoutManager(context)
            leaderboard_run_players.adapter = LeaderboardRunPlayersAdapter(viewModel, run.run.playersToDisplay)

            leaderboard_run_place.text = RunsTextUtils.setPlaceForLeaderboard(run.place)

            leaderboard_run_time.text = RunTimeConverter.from(run.run.times.primary_t)
        }
    }
}