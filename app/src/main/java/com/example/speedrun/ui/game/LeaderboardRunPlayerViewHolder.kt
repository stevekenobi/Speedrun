package com.example.speedrun.ui.game

import android.view.View
import com.example.network.model.dto.UserDto
import com.example.network.utils.enums.UserEnums
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
            if (nameStyle?.style == UserEnums.STYLE_SOLID) {
                latest_player.setTextColor(UserColorUtils.setSolidColor(nameStyle))
            } else if (nameStyle?.style == UserEnums.STYLE_GRADIENT) {
                latest_player.paint.shader = UserColorUtils.setGradientColor(nameStyle)
            }

            latest_player.setOnClickListener {
                viewModel?.leaderboardUserClickedLiveData?.value = player.id
            }
        }
    }
}