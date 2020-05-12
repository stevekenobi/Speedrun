package com.example.speedrun.ui.main

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.speedrun.model.LatestGameModel
import com.example.speedrun.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_latest_game.view.*

class LatestGameViewHolder(val viewModel: LatestRunsViewModel?, itemView: View) : BaseViewHolder(itemView) {

    init {
        viewHolderComponent()?.inject(this)
    }

    fun bind(game: LatestGameModel) {
        itemView.apply {

            Glide.with(this).load(game.imageURI).into(latest_game_image)
            latest_game_image.setOnClickListener {
                viewModel?.latestGamePressedLiveData?.value = game.id
            }

            latest_game_name.text = game.name
            latest_game_name.setOnClickListener {
                viewModel?.latestGamePressedLiveData?.value = game.id
            }

            latest_game_runs.layoutManager = LinearLayoutManager(context)
            latest_game_runs.adapter = LatestRunAdapter(viewModel, game.runs)
        }
    }
}