package com.example.speedrun.ui.main.series

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.dto.SeriesDto
import com.example.speedrun.R

class SeriesAdapter (val viewModel: SeriesViewModel?, val seriesList: List<SeriesDto>): RecyclerView.Adapter<SeriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular_game, parent, false)
        return SeriesViewHolder(
            viewModel,
            view
        )
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.bind(seriesList[position])
    }

}