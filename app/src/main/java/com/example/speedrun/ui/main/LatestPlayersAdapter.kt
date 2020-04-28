package com.example.speedrun.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.dto.PlayerDto
import com.example.speedrun.R

class LatestPlayersAdapter(val playerList: List<PlayerDto>) :
    RecyclerView.Adapter<LatestPlayersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestPlayersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_latest_player, parent, false)
        return LatestPlayersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: LatestPlayersViewHolder, position: Int) {
        holder.bind(playerList[position])
    }

}