package com.example.speedrun.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.speedrun.R
import com.example.speedrun.model.LatestGameModel

class LatestGameAdapter(private val latestGameList: List<LatestGameModel>) :
    RecyclerView.Adapter<LatestGameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestGameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_latest_game, parent, false)
        return LatestGameViewHolder(view)
    }

    override fun getItemCount() = latestGameList.size

    override fun onBindViewHolder(holder: LatestGameViewHolder, position: Int) {
        holder.bind(latestGameList[position])
    }

}