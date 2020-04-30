package com.example.speedrun.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.speedrun.R
import kotlinx.android.synthetic.main.fragment_leaderboard.*

class LeaderboardFragment : Fragment() {

    companion object {
        const val KEY_CATEGORY_NAME = "category_name"

        fun newInstance(categoryname: String): Fragment {
            val fragment = LeaderboardFragment()

            fragment.arguments = Bundle().apply {
                putString(KEY_CATEGORY_NAME, categoryname)
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

        val categoryName = arguments?.getString(KEY_CATEGORY_NAME)

        leader_category_name.text = categoryName
    }
}