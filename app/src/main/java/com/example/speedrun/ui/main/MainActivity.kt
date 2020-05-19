package com.example.speedrun.ui.main

import android.content.Intent
import android.os.Bundle
import com.example.speedrun.R
import com.example.speedrun.ui.base.BaseActivity
import com.example.speedrun.ui.game.GameDetailsActivity
import com.example.speedrun.ui.main.latest.LatestRunsFragment
import com.example.speedrun.ui.main.popular.PopularGamesFragment
import com.example.speedrun.ui.run.RunDetailsActivity
import com.example.speedrun.ui.user.UserProfileActivity
import com.example.speedrun.utils.ActivityExtras
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainFragmentCommunicator {

    private val latestRunsFragment = LatestRunsFragment.newInstance()
    private val popularGamesFragment = PopularGamesFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createLatestRunsFragment()
        initNavigationBar()
    }

    private fun initNavigationBar() {
        main_navigation_bar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.main_menu_latest_runs -> {
                    createLatestRunsFragment()
                    true
                }
                R.id.main_menu_popular_games -> {
                    createPopularGamesFragment()
                    true
                }
                else -> false
            }
        }
    }

//  TODO fix names of transitions
    private fun createLatestRunsFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction
            .replace(R.id.main_navigation_fragment, latestRunsFragment)
            .commit()
    }

    private fun createPopularGamesFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction
            .replace(R.id.main_navigation_fragment, popularGamesFragment)
            .commit()
    }

    override fun initViewModel() {
    }

    override fun observeViewModel() {
    }

    override fun onPlayerClicked(id: String) {
        val intent = Intent(this, UserProfileActivity::class.java).putExtra(ActivityExtras.EXTRA_USER_ID, id)
        startActivity(intent)
    }

    override fun onGameClicked(id: String) {
        val intent = Intent(this, GameDetailsActivity::class.java).putExtra(ActivityExtras.EXTRA_GAME_ID, id)
        startActivity(intent)
    }

    override fun onRunClicked(id: String) {
        val intent = Intent(this, RunDetailsActivity::class.java).putExtra(ActivityExtras.EXTRA_RUN_ID, id)
        startActivity(intent)
    }

}