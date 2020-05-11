package com.example.speedrun.ui.user

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.speedrun.model.UserGameModel
import com.example.speedrun.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_user_game.view.*

class UserGameViewHolder(val viewModel: UserProfileViewModel?, itemView: View) : BaseViewHolder(itemView) {
    init {
        viewHolderComponent()?.inject(this)
    }

    fun bind(game: UserGameModel) {
        itemView.apply {
            Glide.with(this).load(game.image).into(user_game_image)
            user_game_image.setOnClickListener {
                viewModel?.gameClickedLiveData?.value = game.id
            }
            user_game_name.text = game.name
            user_game_name.setOnClickListener {
                viewModel?.gameClickedLiveData?.value = game.id
            }

            user_game_runs.layoutManager = LinearLayoutManager(context)
            user_game_runs.adapter = UserRunsAdapter(viewModel, game.runs)
        }
    }
}