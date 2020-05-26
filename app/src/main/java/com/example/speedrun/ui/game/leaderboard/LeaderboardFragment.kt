package com.example.speedrun.ui.game.leaderboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speedrun.R
import com.example.speedrun.extensions.withArguments
import com.example.speedrun.ui.base.BaseFragment
import com.example.speedrun.ui.run.RunDetailsActivity
import com.example.speedrun.ui.user.UserProfileActivity
import com.example.speedrun.utils.Constants
import kotlinx.android.synthetic.main.fragment_leaderboard.*
import kotlin.math.floor

class LeaderboardFragment : BaseFragment() {

    companion object {
        const val KEY_GAME_ID = "game_id"
        const val KEY_CATEGORY_ID = "category_id"
        const val KEY_LEVEL_ID = "level_id"

        fun newInstance(gameId: String, levelId: String?, categoryId: String): Fragment {
            val fragment = LeaderboardFragment()

            fragment.withArguments {
                putString(KEY_GAME_ID, gameId)
                putString(KEY_LEVEL_ID, levelId)
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
        val levelId = arguments?.getString(KEY_LEVEL_ID)

        if (levelId.isNullOrEmpty())
            viewModel?.getLeaderboard(gameId, categoryId)
        else
            viewModel?.getLevelLeaderboard(gameId, levelId, categoryId)

        initUi()

    }

    override fun initViewModel() {
        viewModel = viewModelFactory.create(LeaderboardViewModel::class.java)
    }

    override fun observeViewModel() {
        viewModel?.isLoadingLiveData?.observe(this, Observer {
            if (it == null)
                return@Observer

            if (it) {
                leaderboard_loader.visibility = View.VISIBLE
                category_leaderboard.visibility = View.GONE
            } else {
                leaderboard_loader.visibility = View.GONE
                category_leaderboard.visibility = View.VISIBLE
            }
        })


        viewModel?.leaderboardLiveData?.observe(this, Observer { leaderboardRunList ->
            category_leaderboard.adapter = LeaderboardRunsAdapter(
                    viewModel,
                    leaderboardRunList,
                    leaderboardRunList.any { run ->
                        run.run.times.primary_t != floor(run.run.times.primary_t)
                    })
        })

        viewModel?.leaderboardRunClickedLiveData?.observe(this, Observer { leaderboardRunId ->
            if (leaderboardRunId.isNullOrEmpty())
                return@Observer

            val intent = Intent(activity, RunDetailsActivity::class.java).putExtra(Constants.ACTIVITY_EXTRA_RUN_ID, leaderboardRunId)
            activity?.startActivity(intent)
        })

        viewModel?.leaderboardUserClickedLiveData?.observe(this, Observer { userId ->
            if (userId.isNullOrEmpty())
                return@Observer

            val intent = Intent(activity, UserProfileActivity::class.java).putExtra(Constants.ACTIVITY_EXTRA_USER_ID, userId)

            activity?.startActivity(intent)
        })
    }

    private fun initUi() {
        category_leaderboard.layoutManager = LinearLayoutManager(activity)
    }
}