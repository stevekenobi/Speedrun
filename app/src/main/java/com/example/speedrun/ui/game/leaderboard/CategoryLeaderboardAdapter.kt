package com.example.speedrun.ui.game.leaderboard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.network.model.dto.CategoryDto

class CategoryLeaderboardAdapter(
    fragmentActivity: FragmentActivity,
    private val gameId: String,
    private val categories: List<CategoryDto>,
    private val levelId: String?) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun createFragment(position: Int): Fragment {
        return LeaderboardFragment.newInstance(gameId, levelId, categories[position].id)
    }

}