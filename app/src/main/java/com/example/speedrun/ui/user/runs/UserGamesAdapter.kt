package com.example.speedrun.ui.user.runs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.speedrun.R
import com.example.speedrun.model.UserGameModel
import kotlinx.android.synthetic.main.item_user_game.view.*

class UserGamesAdapter(val viewModel: UserRunsViewModel?, private val gameList: List<UserGameModel>) : RecyclerView.Adapter<UserGamesAdapter.UserGameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserGameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_game, parent, false)
        return UserGameViewHolder(viewModel, view)
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: UserGameViewHolder, position: Int) {
        holder.bind(gameList[position])
    }

    class UserGameViewHolder(val viewModel: UserRunsViewModel?, itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: UserGameModel) {
            itemView.apply {
                Glide.with(this)
                    .load(game.image)
                    .apply(RequestOptions().override(game.imageWidth ?: 50, game.imageHeight ?: 50))
                    .into(user_game_image)

                user_game_image.setOnClickListener {
                    viewModel?.gameClickedLiveData?.value = game.id
                }
                user_game_name.text = game.name
                user_game_name.setOnClickListener {
                    viewModel?.gameClickedLiveData?.value = game.id
                }

                user_game_runs.layoutManager = LinearLayoutManager(context)
                user_game_runs.adapter =
                    UserRunsAdapter(viewModel, game.runs, game.showMills)
            }
        }
    }

}