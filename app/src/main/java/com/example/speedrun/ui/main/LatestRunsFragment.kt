package com.example.speedrun.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speedrun.R
import com.example.speedrun.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_latest_runs.*

class LatestRunsFragment : BaseFragment() {
    var viewModel: LatestRunsViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_latest_runs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()

        viewModel?.getLatestRuns()
    }

    private fun initUi() {
        main_rv_latest_runs.layoutManager = LinearLayoutManager(context)
        main_rv_latest_runs.addItemDecoration(ItemDivideDecorator(80))
    }

    override fun initViewModel() {
        viewModel = viewModelFactory.create(LatestRunsViewModel::class.java)
    }

    override fun observeViewModel() {
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

            (activity as MainFragmentCommunicator).onPlayerClicked(it)
        })

        viewModel?.latestGamePressedLiveData?.observe(this, Observer {
            if (it.isNullOrEmpty()) {
                return@Observer
            }

            (activity as MainFragmentCommunicator).onGameClicked(it)
        })

        viewModel?.latestRunPressedLiveData?.observe(this, Observer {
            if (it.isNullOrEmpty()) {
                return@Observer
            }

            (activity as MainFragmentCommunicator).onRunClicked(it)
        })

    }

}