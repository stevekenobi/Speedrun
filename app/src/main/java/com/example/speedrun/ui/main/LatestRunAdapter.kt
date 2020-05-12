package com.example.speedrun.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.dto.LatestRunDto
import com.example.speedrun.R

class LatestRunAdapter(val viewModel: LatestRunsViewModel?, val runs: List<LatestRunDto>) : RecyclerView.Adapter<LatestRunViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestRunViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_latest_run, parent, false)
        return LatestRunViewHolder(viewModel, view)
    }

    override fun getItemCount() = runs.size

    override fun onBindViewHolder(holder: LatestRunViewHolder, position: Int) {
        holder.bind(runs[position])
    }
}