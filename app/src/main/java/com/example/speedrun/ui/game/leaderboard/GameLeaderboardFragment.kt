package com.example.speedrun.ui.game.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.network.model.dto.CategoryDto
import com.example.network.utils.enums.CategoryEnums
import com.example.speedrun.R
import com.example.speedrun.extensions.withArguments
import com.example.speedrun.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_leaderboard_view_pager.*

class GameLeaderboardFragment : BaseFragment() {
    companion object {

        const val KEY_GAME_ID = "game_id"
        const val KEY_LEVEL_ID = "level_id"
        fun newInstance(gameId: String?): GameLeaderboardFragment {
            val fragment =
                GameLeaderboardFragment()

            fragment.withArguments {
                putString(KEY_GAME_ID, gameId)
            }

            return fragment
        }

        fun newInstance(gameId: String?, levelId: String?): GameLeaderboardFragment {
            val fragment =
                GameLeaderboardFragment()

            fragment.arguments = Bundle().apply {
                putString(KEY_GAME_ID, gameId)
                putString(KEY_LEVEL_ID, levelId)
            }

            return fragment
        }
    }

    var viewModel: GameLeaderboardViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_leaderboard_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()

        val gameId = arguments?.getString(KEY_GAME_ID)
        val levelId = arguments?.getString(KEY_LEVEL_ID)
        viewModel?.getCategories(gameId, levelId)
    }

    private fun createViewPager(categories: List<CategoryDto>) {

        val levelId = arguments?.getString(KEY_LEVEL_ID)

        var mCategories = categories
        if (categories.any {
                it.type == CategoryEnums.TYPE_PER_GAME
            }) {
            mCategories = categories.filter {
                it.type == CategoryEnums.TYPE_PER_GAME
            }
        }

        pager.adapter =
            CategoryLeaderboardAdapter(
                activity!!,
                arguments?.getString(KEY_GAME_ID)!!,
                mCategories,
                levelId
            )

        val tabTitles = mCategories.map {
            it.name
        }

        TabLayoutMediator(tab_layout, pager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    private fun initUi() {
    }

    override fun initViewModel() {
        viewModel = viewModelFactory.create(GameLeaderboardViewModel::class.java)
    }

    override fun observeViewModel() {
        viewModel?.categoriesLiveData?.observe(this, Observer {
            createViewPager(it)
        })
    }

}