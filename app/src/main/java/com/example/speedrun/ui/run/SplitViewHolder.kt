package com.example.speedrun.ui.run

import android.view.View
import com.example.network.model.splits.SplitsDto
import com.example.speedrun.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_split.view.*

class SplitViewHolder(itemView: View) : BaseViewHolder(itemView) {

    fun bind(split: SplitsDto) {
        itemView.apply {
            split_name.text = split.name

            split_time.text = split.duration.toString()
        }
    }
}