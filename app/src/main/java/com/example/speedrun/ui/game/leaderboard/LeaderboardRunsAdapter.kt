package com.example.speedrun.ui.game.leaderboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.dto.LeaderboardRunDto
import com.example.speedrun.R
import com.example.speedrun.utils.RunTimeConverter
import com.example.speedrun.utils.RunsTextUtils
import kotlinx.android.synthetic.main.item_leaderboard_run.view.*

class LeaderboardRunsAdapter(val viewModel: LeaderboardViewModel?, val runs: List<LeaderboardRunDto>, val showMills: Boolean) : RecyclerView.Adapter<LeaderboardRunsAdapter.LeaderboardRunViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardRunViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_leaderboard_run, parent, false)
        return LeaderboardRunViewHolder(viewModel, view, showMills)
    }

    override fun getItemCount(): Int {
        return runs.size
    }

    override fun onBindViewHolder(holder: LeaderboardRunViewHolder, position: Int) {
        holder.bind(runs[position])
    }

    class LeaderboardRunViewHolder(val viewModel: LeaderboardViewModel?, itemView: View, val showMills: Boolean) : RecyclerView.ViewHolder(itemView) {

        fun bind(run: LeaderboardRunDto) {
            itemView.apply {
                setOnClickListener {
                    viewModel?.leaderboardRunClickedLiveData?.value = run.run.id
                }
                leaderboard_run_players.layoutManager = LinearLayoutManager(context)
                leaderboard_run_players.adapter = LeaderboardRunPlayersAdapter(viewModel, run.run.playersToDisplay)

                leaderboard_run_place.text = RunsTextUtils.setPlaceForLeaderboard(run.place)

                leaderboard_run_time.text = RunTimeConverter.from(run.run.times.primary_t, showMills)
            }
        }
    }

}