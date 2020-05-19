package com.example.speedrun.ui.main.latest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.dto.UserDto
import com.example.speedrun.R

class LatestPlayersAdapter(
    val viewModel: LatestRunsViewModel?,
    private val playerList: List<UserDto>
) :
    RecyclerView.Adapter<LatestPlayersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestPlayersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_latest_player, parent, false)
        return LatestPlayersViewHolder(
            viewModel,
            view
        )
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: LatestPlayersViewHolder, position: Int) {
        holder.bind(playerList[position])
    }

}