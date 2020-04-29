package com.example.speedrun.ui.main

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.view.View
import com.example.network.model.dto.UserDto
import com.example.speedrun.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_latest_player.view.*

class LatestPlayersViewHolder(itemView: View) : BaseViewHolder(itemView) {
    init {
        viewHolderComponent()?.inject(this)
    }

    fun bind(player: UserDto) {
        itemView.apply {
            if (player.role == "guest")
                latest_player.text = player.name
            else
                latest_player.text = player.names?.international

            val textShader = LinearGradient(
                0f, 0f, 0f, 20f,
                intArrayOf(
                    Color.parseColor(player.nameStyle.colorFrom.light),
                    Color.parseColor(player.nameStyle.colorTo.light)
                ),
                floatArrayOf(0f, 1f),
                Shader.TileMode.CLAMP
            )
            latest_player.paint.shader = textShader
        }
    }
}