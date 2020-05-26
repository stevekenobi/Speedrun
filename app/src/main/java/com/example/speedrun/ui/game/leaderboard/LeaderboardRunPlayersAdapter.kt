package com.example.speedrun.ui.game.leaderboard

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

class LeaderboardRunPlayersAdapter(
    val viewModel: LeaderboardViewModel?,
    val players: List<UserDto>
) : RecyclerView.Adapter<LeaderboardRunPlayersAdapter.LeaderboardRunPlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardRunPlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_latest_player, parent, false)

        return LeaderboardRunPlayerViewHolder(viewModel, view)
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: LeaderboardRunPlayerViewHolder, position: Int) {
        holder.bind(players[position])
    }

    class LeaderboardRunPlayerViewHolder(val viewModel: LeaderboardViewModel?, itemView: View) : RecyclerView.ViewHolder(itemView) {

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

}