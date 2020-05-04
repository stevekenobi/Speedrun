package com.example.speedrun.ui.game

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.network.model.dto.LeaderboardRunDto

class CategoryLeaderboardAdapter(
    fragmentActivity: FragmentActivity,
    private val leaderboards: List<List<LeaderboardRunDto>>
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return leaderboards.size
    }

    override fun createFragment(position: Int): Fragment {
        return LeaderboardFragment.newInstance(leaderboards[position] as ArrayList<LeaderboardRunDto>)
    }

}