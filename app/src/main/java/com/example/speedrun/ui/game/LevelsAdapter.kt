package com.example.speedrun.ui.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.dto.LevelDto
import com.example.speedrun.R

class LevelsAdapter(val viewModel: GameLeaderboardViewModel?, val levelList: List<LevelDto>) : RecyclerView.Adapter<LevelViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_level, parent, false)
        return LevelViewHolder(viewModel, view)
    }

    override fun getItemCount(): Int {
        return levelList.size
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        holder.bind(levelList[position])
    }

}