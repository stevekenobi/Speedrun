package com.example.speedrun.ui.game

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.network.model.dto.GameDetailsDto

class CategoryLeaderboardAdapter(
    fragmentActivity: FragmentActivity,
    private val game: GameDetailsDto
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return game.categories.data.size
    }

    override fun createFragment(position: Int): Fragment {
        return LeaderboardFragment.newInstance(game.categories.data[position].name)
    }

}