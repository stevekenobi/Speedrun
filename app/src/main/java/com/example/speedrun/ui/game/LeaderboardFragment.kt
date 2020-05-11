package com.example.speedrun.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speedrun.R
import com.example.speedrun.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_leaderboard.*

class LeaderboardFragment : BaseFragment() {

    companion object {
        const val KEY_GAME_ID = "game_id"
        const val KEY_CATEGORY_ID = "category_id"

        fun newInstance(gameId: String, categoryId: String): Fragment {
            val fragment = LeaderboardFragment()

            fragment.arguments = Bundle().apply {
                putString(KEY_GAME_ID, gameId)
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

        val gameId = arguments?.getString(KEY_GAME_ID)
        val categoryId = arguments?.getString(KEY_CATEGORY_ID)

        viewModel?.getLeaderboard(gameId, categoryId)

        initUi()

    }

    override fun initViewModel() {
        viewModel = viewModelFactory.create(LeaderboardViewModel::class.java)
    }

    override fun observeViewModel() {
        viewModel?.leaderboardLiveData?.observe(this, Observer {
            category_leaderboard.adapter = LeaderboardRunsAdapter(it)
        })
    }

    private fun initUi() {
        category_leaderboard.layoutManager = LinearLayoutManager(activity)
    }
}