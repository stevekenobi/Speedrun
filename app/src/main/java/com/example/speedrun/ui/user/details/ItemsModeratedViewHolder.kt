package com.example.speedrun.ui.user.details

import android.view.View
import com.example.network.model.dto.GameDto
import com.example.network.model.dto.SeriesDto
import com.example.speedrun.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_game_series_moderate.view.*

class ItemsModeratedViewHolder(val viewModel: UserDetailsViewModel?, itemView: View) :
    BaseViewHolder(itemView) {
    init {
        viewHolderComponent()?.inject(this)
    }

    fun bind(item: Any) {
        itemView.item_moderated.apply {
            when (item) {
                is GameDto -> {
                    text = item.names?.international
                    setOnClickListener { viewModel?.gameClickedLiveData?.value = item.id }
                }
                is SeriesDto -> {
                    text = item.names?.international
                    setOnClickListener { viewModel?.seriesClickedLiveData?.value = item.id }
                }
            }
        }
    }
}