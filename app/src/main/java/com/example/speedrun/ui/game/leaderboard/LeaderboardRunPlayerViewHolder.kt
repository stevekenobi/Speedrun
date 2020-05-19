package com.example.speedrun.ui.game.leaderboard

import android.view.View
import com.example.network.model.dto.UserDto
import com.example.network.utils.NetworkConstants
import com.example.speedrun.ui.base.BaseViewHolder
import com.example.speedrun.utils.RunsTextUtils
import com.example.speedrun.utils.UserColorUtils
import kotlinx.android.synthetic.main.item_latest_player.view.*

class LeaderboardRunPlayerViewHolder(val viewModel: LeaderboardViewModel?, itemView: View) : BaseViewHolder(itemView) {

    init {
        viewHolderComponent()?.inject(this)
    }

    fun bind(player: UserDto) {
        itemView.apply {
            latest_player.text = RunsTextUtils.setPlayerText(player)

            val nameStyle = player.nameStyle
            if (nameStyle?.style == NetworkConstants.STYLE_SOLID) {
                latest_player.setTextColor(UserColorUtils.setSolidColor(nameStyle))
            } else if (nameStyle?.style == NetworkConstants.STYLE_GRADIENT) {
                latest_player.paint.shader = UserColorUtils.setGradientColor(nameStyle)
            }

            latest_player.setOnClickListener {
                viewModel?.leaderboardUserClickedLiveData?.value = player.id
            }
        }
    }
}