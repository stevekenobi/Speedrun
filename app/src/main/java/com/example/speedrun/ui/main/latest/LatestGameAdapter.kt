package com.example.speedrun.ui.main.latest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.speedrun.R
import com.example.speedrun.model.LatestGameModel
import kotlinx.android.synthetic.main.item_latest_game.view.*

class LatestGameAdapter(val viewModel: LatestRunsViewModel?, private val latestGameList: List<LatestGameModel>) :
    RecyclerView.Adapter<LatestGameAdapter.LatestGameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestGameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_latest_game, parent, false)
        return LatestGameViewHolder(
            viewModel,
            view
        )
    }

    override fun getItemCount() = latestGameList.size

    override fun onBindViewHolder(holder: LatestGameViewHolder, position: Int) {
        holder.bind(latestGameList[position])
    }

    class LatestGameViewHolder(val viewModel: LatestRunsViewModel?, itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(game: LatestGameModel) {
            itemView.apply {

                Glide.with(this)
                    .load(game.imageURI)
                    .apply(RequestOptions().override(game.imageWidth ?: 50, game.imageHeight ?: 50))
                    .into(latest_game_image)

                latest_game_image.setOnClickListener {
                    viewModel?.latestGamePressedLiveData?.value = game.id
                }

                latest_game_name.text = game.name
                latest_game_name.setOnClickListener {
                    viewModel?.latestGamePressedLiveData?.value = game.id
                }

                latest_game_runs.layoutManager = LinearLayoutManager(context)
                latest_game_runs.adapter =
                    LatestRunAdapter(
                        viewModel,
                        game.runs,
                        game.showMills
                    )
            }
        }
    }
}