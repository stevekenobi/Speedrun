package com.example.speedrun.ui.game

import android.os.Bundle
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.network.model.dto.GameDetailsDto
import com.example.network.model.dto.LeaderboardRunDto
import com.example.network.utils.CategoryEnums
import com.example.speedrun.R
import com.example.speedrun.ui.base.BaseActivity
import com.example.speedrun.utils.ActivityExtras
import com.example.speedrun.viewmodel.SpeedrunViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_game_details.*
import javax.inject.Inject

class GameDetailsActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: SpeedrunViewModelFactory

    var viewModel: GameDetailsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_details)

        activityComponent?.inject(this)

        initViewModel()

        viewModel?.getGameDetails(intent.getStringExtra(ActivityExtras.EXTRA_GAME_ID))
    }

    private fun initViewModel() {
        viewModel = viewModelFactory.create(GameDetailsViewModel::class.java)

        viewModel?.gameDetailsLiveData?.observe(this, Observer {
            fillGameDetails(it)
            viewModel?.getLeaderboards(it)
        })

        viewModel?.leaderboardsLiveData?.observe(this, Observer {
            if (it == null)
                return@Observer

            createViewPager(it)
        })
    }

    private fun fillGameDetails(game: GameDetailsDto) {
        Glide.with(this).load(game.assets.coverMedium?.uri).into(game_image)
        game_details_name.text = game.names?.international
    }

    private fun createViewPager(leaderboards: List<List<LeaderboardRunDto>>) {
        pager.adapter = CategoryLeaderboardAdapter(this, leaderboards)

        val tabTitles = viewModel?.gameDetailsLiveData?.value?.categories?.data?.filter {
            it.type == CategoryEnums.TYPE_PER_GAME
        }?.map {
            it.name
        }

        TabLayoutMediator(tab_layout, pager) { tab, position ->
            tab.text = tabTitles?.get(position)
        }.attach()
    }
}
