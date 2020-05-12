package com.example.speedrun.ui.main

import android.view.View
import com.example.network.model.dto.UserDto
import com.example.network.utils.UserEnums
import com.example.speedrun.ui.base.BaseViewHolder
import com.example.speedrun.utils.RunsTextUtils
import com.example.speedrun.utils.UserColorUtils
import kotlinx.android.synthetic.main.item_latest_player.view.*

class LatestPlayersViewHolder(val viewModel: LatestRunsViewModel?, itemView: View) :
    BaseViewHolder(itemView) {
    init {
        viewHolderComponent()?.inject(this)
    }

    fun bind(player: UserDto) {
        itemView.apply {
            latest_player.text = RunsTextUtils.setPlayerText(player)

            latest_player.setOnClickListener {
                viewModel?.latestUserPressedLiveData?.value = player.id
            }
            val style = player.nameStyle?.style
            if (style == UserEnums.STYLE_SOLID) {
                latest_player.setTextColor(UserColorUtils.setSolidColor(player.nameStyle))
            } else if (style == UserEnums.STYLE_GRADIENT) {
                latest_player.paint.shader = UserColorUtils.setGradientColor(player.nameStyle)
            }
        }
    }
}