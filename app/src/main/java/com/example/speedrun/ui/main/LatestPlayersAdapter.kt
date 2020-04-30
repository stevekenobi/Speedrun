package com.example.speedrun.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.dto.UserDto
import com.example.speedrun.R
import kotlinx.android.synthetic.main.item_latest_player.view.*

class LatestPlayersAdapter(
    val viewModel: MainViewModel?,
    private val playerList: List<UserDto>
) :
    RecyclerView.Adapter<LatestPlayersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestPlayersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_latest_player, parent, false)
        return LatestPlayersViewHolder(viewModel, view)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: LatestPlayersViewHolder, position: Int) {
        holder.bind(playerList[position])
    }

}