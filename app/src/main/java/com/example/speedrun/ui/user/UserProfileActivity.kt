package com.example.speedrun.ui.user

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.network.model.dto.UserDto
import com.example.network.model.dto.UserRunDto
import com.example.speedrun.R
import com.example.speedrun.model.UserGameModel
import com.example.speedrun.ui.base.BaseActivity
import com.example.speedrun.ui.main.ItemDivideDecorator
import com.example.speedrun.utils.ActivityExtras
import com.example.speedrun.viewmodel.SpeedrunViewModelFactory
import kotlinx.android.synthetic.main.activity_user_profile.*
import javax.inject.Inject

class UserProfileActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: SpeedrunViewModelFactory

    var viewModel: UserProfileViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        activityComponent?.inject(this)

        initUI()

        initViewModel()

        viewModel?.getUserDetails(intent.getStringExtra(ActivityExtras.EXTRA_USER_ID))
    }

    private fun initViewModel() {
        viewModel = viewModelFactory.create(UserProfileViewModel::class.java)

        viewModel?.isLoadingLiveData?.observe(this, Observer {
            if (it == null)
                return@Observer

            if (it) {
                user_profile_loader.visibility = View.VISIBLE
                user_profile_layout.visibility = View.GONE
            } else {
                user_profile_layout.visibility = View.VISIBLE
                user_profile_loader.visibility = View.GONE
            }
        })

        viewModel?.userRunsLiveData?.observe(this, Observer {
            if (it == null)
                return@Observer

            updateUserRuns(it)
        })

        viewModel?.userDetailsLiveData?.observe(this, Observer {
            if (it == null) {
                return@Observer
            }

            updateUserInfo(it)
        })
    }

    private fun initUI() {
        rv_user_runs.apply {
            layoutManager = LinearLayoutManager(this@UserProfileActivity)
            val itemDecoration = ItemDivideDecorator(80)
            addItemDecoration(itemDecoration)
        }
    }

    private fun updateUserInfo(user: UserDto) {
        user_name.text = user.names?.international

        val textShader = LinearGradient(
            0f, 0f, 0f, 20f,
            intArrayOf(
                Color.parseColor(user.nameStyle.colorFrom.light),
                Color.parseColor(user.nameStyle.colorTo.light)
            ),
            floatArrayOf(0f, 1f),
            Shader.TileMode.CLAMP
        )
        user_name.paint.shader = textShader

    }

    private fun updateUserRuns(gameList: List<UserGameModel>) {
        rv_user_runs.adapter = UserGamesAdapter(gameList)
    }
}