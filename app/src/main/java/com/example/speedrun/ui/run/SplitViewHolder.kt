package com.example.speedrun.ui.run

import android.view.View
import com.example.network.model.splits.SplitsDto
import com.example.speedrun.ui.base.BaseViewHolder
import com.example.speedrun.utils.RunTimeConverter
import kotlinx.android.synthetic.main.item_split.view.*

class SplitViewHolder(itemView: View, val showMills: Boolean) : BaseViewHolder(itemView) {

    fun bind(split: SplitsDto) {
        itemView.apply {
            split_name.text = split.name

            split_time.text = RunTimeConverter.from(split.duration, showMills)
        }
    }
}