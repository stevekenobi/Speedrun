package com.example.speedrun.ui.main.series

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.network.model.dto.SeriesDto
import com.example.speedrun.R
import kotlinx.android.synthetic.main.item_popular_game.view.*

class SeriesAdapter (val viewModel: SeriesViewModel?, val seriesList: List<SeriesDto>): RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular_game, parent, false)
        return SeriesViewHolder(viewModel, view)
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.bind(seriesList[position])
    }

    class SeriesViewHolder(val viewModel: SeriesViewModel?, itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(series: SeriesDto) {
            itemView.apply {
                setOnClickListener {
                    viewModel?.seriesClickedLiveData?.value = series.id
                }
                Glide
                    .with(this)
                    .load(series.assets.coverLarge?.uri)
                    .apply(RequestOptions().override(series.assets.coverLarge?.width ?: 100,series.assets.coverLarge?.height ?: 100))
                    .into(recent_game_image)
                recent_game_name.text = series.names?.international
            }
        }
    }
}