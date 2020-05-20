package com.example.speedrun.ui.main.series

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.network.model.dto.SeriesDto
import com.example.speedrun.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_popular_game.view.*

class SeriesViewHolder(val viewModel: SeriesViewModel?, itemView: View) : BaseViewHolder(itemView) {
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