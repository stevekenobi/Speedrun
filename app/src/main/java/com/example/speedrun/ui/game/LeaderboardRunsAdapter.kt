package com.example.speedrun.ui.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.dto.LeaderboardRunDto
import com.example.speedrun.R

class LeaderboardRunsAdapter(val runs: List<LeaderboardRunDto>) : RecyclerView.Adapter<LeaderboardRunViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardRunViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_leaderboard_run, parent, false)
        return LeaderboardRunViewHolder(view)
    }

    override fun getItemCount(): Int {
        return runs.size
    }

    override fun onBindViewHolder(holder: LeaderboardRunViewHolder, position: Int) {
        holder.bind(runs[position])
    }

}