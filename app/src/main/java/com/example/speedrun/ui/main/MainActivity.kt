package com.example.speedrun.ui.main

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speedrun.R
import com.example.speedrun.ui.base.BaseActivity
import com.example.speedrun.ui.game.GameDetailsActivity
import com.example.speedrun.ui.run.RunDetailsActivity
import com.example.speedrun.ui.user.UserProfileActivity
import com.example.speedrun.utils.ActivityExtras
import com.example.speedrun.viewmodel.SpeedrunViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: SpeedrunViewModelFactory

    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityComponent?.inject(this)

        initViewModel()

        initUi()

        viewModel?.getLatestRuns()
    }

    override fun onBackPressed() {
        buildAlertDialog(
            this,
            R.string.base_alert_title,
            R.string.base_alert_message,
            R.string.base_alert_positive,
            DialogInterface.OnClickListener { _, _ -> finish() },
            R.string.base_alert_negative,
            DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss()},
            true
        ).show()
    }

    private fun initViewModel() {
        viewModel = viewModelFactory.create(MainViewModel::class.java)

        viewModel?.isLoadingLiveData?.observe(this, Observer {
            if (it == null)
                return@Observer

            if (it) {
                main_loader.visibility = View.VISIBLE
                main_layout.visibility = View.GONE
            } else {
                main_refresh.isRefreshing = false
                main_layout.visibility = View.VISIBLE
                main_loader.visibility = View.GONE
            }
        })

        viewModel?.latestRunsLiveData?.observe(this, Observer {
            if (it.isNullOrEmpty()) {
                return@Observer
            }

            main_rv_latest_runs.adapter = LatestGameAdapter(viewModel, it)
            main_rv_latest_runs.isNestedScrollingEnabled = false
        })

        viewModel?.latestUserPressedLiveData?.observe(this, Observer {
            if (it.isNullOrEmpty()) {
                return@Observer
            }

            val intent = Intent(this, UserProfileActivity::class.java).putExtra(ActivityExtras.EXTRA_USER_ID, it)
            startActivity(intent)
        })

        viewModel?.latestGamePressedLiveData?.observe(this, Observer {
            if (it.isNullOrEmpty()) {
                return@Observer
            }

            val intent = Intent(this, GameDetailsActivity::class.java).putExtra(ActivityExtras.EXTRA_GAME_ID, it)
            startActivity(intent)
        })

        viewModel?.latestRunPressedLiveData?.observe(this, Observer {
            if (it.isNullOrEmpty()) {
                return@Observer
            }

            val intent = Intent(this, RunDetailsActivity::class.java).putExtra(ActivityExtras.EXTRA_RUN_ID, it)
            startActivity(intent)
        })
    }

    private fun initUi() {
        main_refresh.setOnRefreshListener {
            viewModel?.getLatestRuns()
        }

        main_rv_latest_runs.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val itemDecoration = ItemDivideDecorator(80)
            addItemDecoration(itemDecoration)
        }
    }

}
