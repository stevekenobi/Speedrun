package com.example.speedrun.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.dto.PlayerDto
import com.example.speedrun.R

class RunPlayerRecyclerViewAdapter(val context: Context, private val players: List<PlayerDto>) : RecyclerView.Adapter<RunPlayerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunPlayerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.main_run_username, parent, false)
        return RunPlayerViewHolder(view)
    }

    override fun getItemCount() = players.size

    override fun onBindViewHolder(holder: RunPlayerViewHolder, position: Int) {
        holder.bind(players[position])
    }
}