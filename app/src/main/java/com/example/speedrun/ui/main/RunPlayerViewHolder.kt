package com.example.speedrun.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.dto.PlayerDto
import com.example.speedrun.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.main_run_username.view.*

class RunPlayerViewHolder(itemView: View) : BaseViewHolder(itemView) {

    init {
        viewHolderComponent()?.inject(this)
    }

    fun bind(player: PlayerDto) {
        itemView.apply {
            run_user_name.text = player.names.international
        }
    }
}