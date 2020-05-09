package com.example.speedrun.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.speedrun.R
import com.example.speedrun.ui.base.BaseFragment

class LeaderboardFragment : BaseFragment() {

    companion object {
        const val KEY_CATEGORY_ID = "category_id"

        fun newInstance(categoryId: String): Fragment {
            val fragment = LeaderboardFragment()

            fragment.arguments = Bundle().apply {
                putString(KEY_CATEGORY_ID, categoryId)
            }
            return fragment
        }
    }

    private var viewModel: LeaderboardViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent?.inject(this)
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

        val categoryId = arguments?.getString(KEY_CATEGORY_ID)

    }

    override fun initViewModel() {
        viewModel = viewModelFactory.create(LeaderboardViewModel::class.java)
    }

    override fun observeViewModel() {
        Toast.makeText(activity, "So far so good", Toast.LENGTH_SHORT).show()
    }
}