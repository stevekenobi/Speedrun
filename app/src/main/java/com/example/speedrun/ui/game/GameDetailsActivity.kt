package com.example.speedrun.ui.game

import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.speedrun.R
import com.example.speedrun.ui.base.BaseActivity
import com.example.speedrun.utils.MockingIDs
import kotlinx.android.synthetic.main.activity_game_details.*

class GameDetailsActivity : BaseActivity(), FragmentCommunicator {

    private var gameId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_details)

        activityComponent?.inject(this)

        initDrawer()

        initFragments()
    }

    override fun initViewModel() {
    }

    override fun observeViewModel() {
    }

    override fun onBackPressed() {
        if (drawer_layout != null && drawer_layout.isDrawerOpen(GravityCompat.START))
            drawer_layout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    private fun initFragments() {
        gameId = MockingIDs.LIJ_GAME_ID
        val drawerFragment = GameDetailsFragment.newInstance(gameId)
        val viewPagerFragment = GameLeaderboardFragment.newInstance(gameId)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.game_details_fragment, drawerFragment)
        fragmentTransaction.replace(R.id.game_leaderboard_layout_fragment, viewPagerFragment)
        fragmentTransaction.commit()
    }

    private fun initDrawer() {
        drawer_layout.addDrawerListener(
            object : DrawerLayout.DrawerListener {
                override fun onDrawerStateChanged(newState: Int) {
                }

                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    drawer_layout.bringChildToFront(drawerView)
                    drawer_layout.requestLayout()
                }

                override fun onDrawerClosed(drawerView: View) {
                }

                override fun onDrawerOpened(drawerView: View) {
                }

            }
        )
    }

    override fun onILClicked(levelId: String?) {
        val viewPagerFragment = GameLeaderboardFragment.newInstance(gameId, levelId)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.game_leaderboard_layout_fragment, viewPagerFragment)
        fragmentTransaction.commit()
    }
}
