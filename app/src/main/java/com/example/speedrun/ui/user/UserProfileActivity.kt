package com.example.speedrun.ui.user

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import com.example.network.utils.NetworkConstants
import com.example.speedrun.R
import com.example.speedrun.ui.base.BaseActivity
import com.example.speedrun.ui.game.GameDetailsActivity
import com.example.speedrun.ui.run.RunDetailsActivity
import com.example.speedrun.ui.user.details.UserDetailsFragment
import com.example.speedrun.ui.user.runs.UserRunsFragment
import com.example.speedrun.utils.Constants
import com.example.speedrun.utils.UserColorUtils
import kotlinx.android.synthetic.main.activity_user_details.*
import kotlinx.android.synthetic.main.layout_user_menu.*

class UserProfileActivity : BaseActivity(), UserFragmentCommunicator {
    private var viewModel: UserMenuViewModel? = null

    private var userId: String? = null

    private var userRunsFragment: UserRunsFragment? = null
    private var userDetailsFragment: UserDetailsFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        activityComponent?.inject(this)

        userId = Constants.ACTIVITY_EXTRA_USER_ID
        viewModel?.getUserDetails(userId)
        initDrawer()
        initFragments()
        initMenu()
        createUserRunsFragment()
    }

    override fun onBackPressed() {
        if (user_drawer_layout != null && user_drawer_layout.isDrawerOpen(GravityCompat.START))
            user_drawer_layout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    private fun initFragments() {
        userRunsFragment = UserRunsFragment.newInstance(userId)
        userDetailsFragment = UserDetailsFragment.newInstance(userId)
    }

    private fun createUserRunsFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.user_details_fragment, userRunsFragment!!)
        fragmentTransaction.commit()
    }

    private fun createInfoFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.user_details_fragment, userDetailsFragment!!)
        fragmentTransaction.commit()
    }

    private fun initDrawer() {
        user_drawer_layout.addDrawerListener(
            object : DrawerLayout.DrawerListener {
                override fun onDrawerStateChanged(newState: Int) {
                }

                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    user_drawer_layout.bringChildToFront(drawerView)
                    user_drawer_layout.requestLayout()
                }

                override fun onDrawerClosed(drawerView: View) {
                }

                override fun onDrawerOpened(drawerView: View) {
                }

            }
        )
    }

    private fun initMenu() {
        user_details_menu.setNavigationItemSelectedListener {item ->
            when (item.itemId) {
                R.id.user_menu_full -> {
                    createUserRunsFragment()
                    true
                }
                R.id.user_menu_info -> {
                    createInfoFragment()
                    true
                }
                else -> false
            }
        }
    }

    override fun initViewModel() {
        viewModel = viewModelFactory.create(UserMenuViewModel::class.java)
    }

    override fun observeViewModel() {
        viewModel?.userDetailsLiveData?.observe(this, Observer { user ->
            if (user == null)
                return@Observer

            user_name.text = user.name
            val style = user.nameStyle?.style
            if (style == NetworkConstants.STYLE_SOLID) {
                user_name.setTextColor(UserColorUtils.setSolidColor(user.nameStyle))
            } else if (style == NetworkConstants.STYLE_GRADIENT) {
                user_name.paint.shader = UserColorUtils.setGradientColor(user.nameStyle)
            }
        })
    }

    override fun onGameClicked(id: String) {
        val intent = Intent(this, GameDetailsActivity::class.java)
        intent.putExtra(Constants.ACTIVITY_EXTRA_GAME_ID, id)
        startActivity(intent)
    }

    override fun onSeriesClicked(id: String) {
        Toast.makeText(this, "Not implemented", Toast.LENGTH_SHORT).show()
    }

    override fun onRunClicked(id: String) {
        val intent = Intent(this, RunDetailsActivity::class.java)
        intent.putExtra(Constants.ACTIVITY_EXTRA_RUN_ID, id)
        startActivity(intent)
    }
}