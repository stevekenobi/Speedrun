package com.example.speedrun.ui.main

import android.view.View
import com.example.network.model.dto.PlayerDto
import com.example.speedrun.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_latest_player.view.*

class LatestPlayersViewHolder(itemView: View) : BaseViewHolder(itemView) {
    init {
        viewHolderComponent()?.inject(this)
    }

    fun bind(player: PlayerDto) {
        itemView.latest_player.text = player.names.international
    }
}