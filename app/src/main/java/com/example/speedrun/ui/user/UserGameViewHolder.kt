package com.example.speedrun.ui.user

import android.view.View
import com.bumptech.glide.Glide
import com.example.speedrun.model.UserGameModel
import com.example.speedrun.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_user_game.view.*

class UserGameViewHolder(itemView: View) : BaseViewHolder(itemView) {
    init {
        viewHolderComponent()?.inject(this)
    }

    fun bind(game: UserGameModel) {
        itemView.apply {
            Glide.with(this).load(game.image)

            user_game_name.text = game.name
        }
    }
}