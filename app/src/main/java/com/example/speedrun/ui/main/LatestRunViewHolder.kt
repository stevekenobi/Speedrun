package com.example.speedrun.ui.main

import android.view.View
import com.example.network.model.dto.LatestRunDto
import com.example.speedrun.ui.base.BaseViewHolder
import com.example.speedrun.utils.RunTimeConverter
import kotlinx.android.synthetic.main.item_latest_run.view.*

class LatestRunViewHolder(itemView: View) : BaseViewHolder(itemView) {

    init {
        viewHolderComponent()?.inject(this)
    }

    fun bind(run: LatestRunDto) {
        itemView.apply {
            val info = run.category.data.name + "   " + RunTimeConverter.from(run.times.primary_t)

            latest_game_run_info.text = info
        }
    }
}