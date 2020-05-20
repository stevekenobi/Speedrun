package com.example.speedrun.ui.user

import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.speedrun.R
import com.example.speedrun.ui.base.BaseActivity
import com.example.speedrun.ui.user.runs.UserRunsFragment
import com.example.speedrun.utils.Constants
import kotlinx.android.synthetic.main.activity_user_details.*

class UserProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        activityComponent?.inject(this)

        initDrawer()

        initFragments()
    }

    override fun onBackPressed() {
        if (user_drawer_layout != null && user_drawer_layout.isDrawerOpen(GravityCompat.START))
            user_drawer_layout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    private fun initFragments() {
        val userId = Constants.NORDANIX_USER_ID
        val userRunsFragment = UserRunsFragment.newInstance(userId)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.user_details_fragment, userRunsFragment)
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

    override fun initViewModel() {
    }

    override fun observeViewModel() {
    }
}