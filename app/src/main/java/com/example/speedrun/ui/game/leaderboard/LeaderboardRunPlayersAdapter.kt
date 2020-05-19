package com.example.speedrun.ui.game.leaderboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.dto.UserDto
import com.example.speedrun.R

class LeaderboardRunPlayersAdapter(
    val viewModel: LeaderboardViewModel?,
    val players: List<UserDto>
) : RecyclerView.Adapter<LeaderboardRunPlayerViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LeaderboardRunPlayerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_latest_player, parent, false)

        return LeaderboardRunPlayerViewHolder(
            viewModel,
            view
        )
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: LeaderboardRunPlayerViewHolder, position: Int) {
        holder.bind(players[position])
    }

}