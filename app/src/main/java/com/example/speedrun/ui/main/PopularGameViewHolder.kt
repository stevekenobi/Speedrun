package com.example.speedrun.ui.main

import android.view.View
import com.bumptech.glide.Glide
import com.example.network.model.dto.GameDto
import com.example.speedrun.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_popular_game.view.*

class PopularGameViewHolder(val viewModel: PopularGamesViewModel?, itemView: View) : BaseViewHolder(itemView) {
    fun bind(game: GameDto) {
        itemView.apply {
            setOnClickListener {
                viewModel?.gameClickedLiveData?.value = game.id
            }
            Glide.with(this).load(game.assets.coverLarge?.uri).into(recent_game_image)
            recent_game_name.text = game.names?.international
        }
    }
}