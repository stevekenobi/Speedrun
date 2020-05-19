package com.example.speedrun.ui.main.latest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.dto.LatestRunDto
import com.example.speedrun.R

class LatestRunAdapter(val viewModel: LatestRunsViewModel?, val runs: List<LatestRunDto>, val showMills: Boolean) : RecyclerView.Adapter<LatestRunViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestRunViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_latest_run, parent, false)
        return LatestRunViewHolder(
            viewModel,
            view,
            showMills
        )
    }

    override fun getItemCount() = runs.size

    override fun onBindViewHolder(holder: LatestRunViewHolder, position: Int) {
        holder.bind(runs[position])
    }
}