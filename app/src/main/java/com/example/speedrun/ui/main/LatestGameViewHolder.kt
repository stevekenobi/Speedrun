package com.example.speedrun.ui.main

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.network.model.dto.LatestRunDto
import com.example.speedrun.model.LatestGameModel
import com.example.speedrun.ui.base.BaseViewHolder
import com.example.speedrun.utils.RunTimeConverter
import kotlinx.android.synthetic.main.item_latest_game.view.*

class LatestGameViewHolder(itemView: View) : BaseViewHolder(itemView) {

    init {
        viewHolderComponent()?.inject(this)
    }

    fun bind(game: LatestGameModel) {
        itemView.apply {

            Glide.with(this).load(game.imageURI).into(latest_game_image)
            latest_game_name.text = game.name

            latest_game_runs.layoutManager = LinearLayoutManager(context)
            latest_game_runs.adapter = LatestRunAdapter(game.runs)
        }
    }
}