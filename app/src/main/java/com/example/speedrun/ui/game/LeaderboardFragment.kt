package com.example.speedrun.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.network.model.dto.LeaderboardRunDto
import com.example.speedrun.R
import kotlinx.android.synthetic.main.fragment_leaderboard.*

class LeaderboardFragment : Fragment() {

    companion object {
        const val KEY_CATEGORY_LEADERBOARD = "category_leaderboard"

        fun newInstance(leaderboard: ArrayList<LeaderboardRunDto>): Fragment {
            val fragment = LeaderboardFragment()

            fragment.arguments = Bundle().apply {
                putParcelableArrayList(KEY_CATEGORY_LEADERBOARD, leaderboard)
            }
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_leaderboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val leaderboard = arguments?.getParcelableArrayList<LeaderboardRunDto>(KEY_CATEGORY_LEADERBOARD)?.toList()

        category_leaderboard.layoutManager = LinearLayoutManager(context)
        if (leaderboard.isNullOrEmpty())
            return

        category_leaderboard.adapter = LeaderboardRunsAdapter(leaderboard)

    }
}