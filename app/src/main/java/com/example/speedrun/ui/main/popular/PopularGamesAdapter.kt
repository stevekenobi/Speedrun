package com.example.speedrun.ui.main.popular

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.network.model.dto.GameDto
import com.example.speedrun.R
import kotlinx.android.synthetic.main.item_popular_game.view.*

class PopularGamesAdapter (val viewModel: PopularGamesViewModel?, val gameList: List<GameDto>): RecyclerView.Adapter<PopularGamesAdapter.PopularGameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularGameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular_game, parent, false)
        return PopularGameViewHolder(viewModel, view)
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: PopularGameViewHolder, position: Int) {
        holder.bind(gameList[position])
    }

    class PopularGameViewHolder(val viewModel: PopularGamesViewModel?, itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: GameDto) {
            itemView.apply {
                setOnClickListener {
                    viewModel?.gameClickedLiveData?.value = game.id
                }
                Glide
                    .with(this)
                    .load(game.assets.coverLarge?.uri)
                    .apply(RequestOptions().override(game.assets.coverLarge?.width ?: 100,game.assets.coverLarge?.height ?: 100))
                    .into(recent_game_image)
                recent_game_name.text = game.names?.international
            }
        }
    }
}