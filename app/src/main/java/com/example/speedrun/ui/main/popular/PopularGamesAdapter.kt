package com.example.speedrun.ui.main.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.dto.GameDto
import com.example.speedrun.R

class PopularGamesAdapter (val viewModel: PopularGamesViewModel?, val gameList: List<GameDto>): RecyclerView.Adapter<PopularGameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularGameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular_game, parent, false)
        return PopularGameViewHolder(
            viewModel,
            view
        )
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: PopularGameViewHolder, position: Int) {
        holder.bind(gameList[position])
    }

}