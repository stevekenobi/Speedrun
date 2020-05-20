package com.example.speedrun.ui.user.runs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.speedrun.R
import com.example.speedrun.model.UserGameModel

class UserGamesAdapter(val viewModel: UserRunsViewModel?, private val gameList: List<UserGameModel>) : RecyclerView.Adapter<UserGameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserGameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_game, parent, false)
        return UserGameViewHolder(
            viewModel,
            view
        )
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: UserGameViewHolder, position: Int) {
        holder.bind(gameList[position])
    }

}