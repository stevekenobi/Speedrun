package com.example.speedrun.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speedrun.R
import com.example.speedrun.ui.base.BaseActivity
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

    private fun initViewModel() {
        viewModel = viewModelFactory.create(MainViewModel::class.java)

        viewModel?.isLoadingLiveData?.observe(this, Observer {
            if (it == null)
                return@Observer

            if (it){
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

            main_rv_latest_runs.layoutManager = LinearLayoutManager(this)
            val itemDecoration = ItemDivideDecorator(80)
            main_rv_latest_runs.addItemDecoration(itemDecoration)
            main_rv_latest_runs.adapter = LatestGameAdapter(it)
        })
    }

    private fun initUi() {
        main_refresh.setOnRefreshListener {
            viewModel?.getLatestRuns()
        }
    }

}
