package com.example.speedrun.ui.game

import android.view.View
import com.example.network.model.dto.LevelDto
import com.example.speedrun.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_level.view.*

class LevelViewHolder(val viewModel: GameDetailsViewModel?, itemView: View) :
    BaseViewHolder(itemView) {
    init {
        viewHolderComponent()?.inject(this)
    }

    fun bind(level: LevelDto) {
        itemView.apply {
            level_name.text = level.name

            setOnClickListener {
                if (viewModel?.levelSelectedLiveData?.value == level.id)
                    viewModel.levelSelectedLiveData.value = null
                else
                    viewModel?.levelSelectedLiveData?.value = level.id
            }
        }
    }
}