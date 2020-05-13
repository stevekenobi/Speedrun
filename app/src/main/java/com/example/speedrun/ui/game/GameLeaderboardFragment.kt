package com.example.speedrun.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.network.model.dto.CategoryDto
import com.example.network.model.dto.LevelDto
import com.example.network.utils.enums.CategoryEnums
import com.example.speedrun.R
import com.example.speedrun.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_leaderboard_view_pager.*

class GameLeaderboardFragment : BaseFragment() {
    companion object {

        const val KEY_GAME_ID = "game_id"
        const val KEY_SHOW_MISC_CATEGORIES = "show_misc_categories"
        fun newInstance(gameId: String?, showMisc: Boolean): GameLeaderboardFragment {
            val fragment = GameLeaderboardFragment()

            fragment.arguments = Bundle().apply {
                putString(KEY_GAME_ID, gameId)
                putBoolean(KEY_SHOW_MISC_CATEGORIES, showMisc)
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
        viewModel?.getCategories(gameId)
        viewModel?.getLevels(gameId)
    }

    private fun createViewPager(categories: List<CategoryDto>) {
        val mustShowMisc = arguments?.getBoolean(KEY_SHOW_MISC_CATEGORIES)
        val mCategories = categories.filter {
            it.type == CategoryEnums.TYPE_PER_GAME && it.miscellaneous == mustShowMisc
        }

        pager.adapter =
            CategoryLeaderboardAdapter(activity!!, arguments?.getString(KEY_GAME_ID)!!, mCategories, null)

        val tabTitles = mCategories.map {
            it.name
        }

        TabLayoutMediator(tab_layout, pager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    private fun createViewPagerForLevel(levelId: String) {
        val mCategories = viewModel?.levelsLiveData?.value?.find {
            it.id == levelId
        }?.categories?.data ?: return

        pager.adapter =
            CategoryLeaderboardAdapter(activity!!, arguments?.getString(KEY_GAME_ID)!!, mCategories, levelId)

        val tabTitles = mCategories.map {
            it.name
        }

        TabLayoutMediator(tab_layout, pager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    private fun createLevelButtons(levels: List<LevelDto>) {
        levels_buttons.adapter = LevelsAdapter(viewModel, levels)
    }

    private fun initUi() {
        levels_buttons.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun initViewModel() {
        viewModel = viewModelFactory.create(GameLeaderboardViewModel::class.java)
    }

    override fun observeViewModel() {
        viewModel?.categoriesLiveData?.observe(this, Observer {
            createViewPager(it)
        })

        viewModel?.levelsLiveData?.observe(this, Observer {
            if (it.isNullOrEmpty())
                return@Observer

            createLevelButtons(it)
        })

        viewModel?.levelSelectedLiveData?.observe(this, Observer {
            if (it.isNullOrEmpty())
                viewModel?.getCategories(arguments?.getString(KEY_GAME_ID))
            else
                createViewPagerForLevel(it)
        })
    }

}