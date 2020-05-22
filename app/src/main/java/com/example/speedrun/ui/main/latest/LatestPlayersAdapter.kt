package com.example.speedrun.ui.main.latest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.dto.UserDto
import com.example.network.utils.NetworkConstants
import com.example.speedrun.R
import com.example.speedrun.utils.RunsTextUtils
import com.example.speedrun.utils.UserColorUtils
import kotlinx.android.synthetic.main.item_latest_player.view.*

class LatestPlayersAdapter(val viewModel: LatestRunsViewModel?, private val playerList: List<UserDto>) : RecyclerView.Adapter<LatestPlayersAdapter.LatestPlayersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestPlayersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_latest_player, parent, false)
        return LatestPlayersViewHolder(
            viewModel,
            view
        )
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: LatestPlayersViewHolder, position: Int) {
        holder.bind(playerList[position])
    }

    class LatestPlayersViewHolder(val viewModel: LatestRunsViewModel?, itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(player: UserDto) {
            itemView.apply {
                latest_player.text = RunsTextUtils.setPlayerText(player)

                latest_player.setOnClickListener {
                    viewModel?.latestUserPressedLiveData?.value = player.id
                }
                val style = player.nameStyle?.style
                if (style == NetworkConstants.STYLE_SOLID) {
                    latest_player.setTextColor(UserColorUtils.setSolidColor(player.nameStyle))
                } else if (style == NetworkConstants.STYLE_GRADIENT) {
                    latest_player.paint.shader = UserColorUtils.setGradientColor(player.nameStyle)
                }
            }
        }
    }

}