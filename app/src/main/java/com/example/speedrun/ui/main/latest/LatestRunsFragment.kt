package com.example.speedrun.ui.main.latest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speedrun.R
import com.example.speedrun.ui.base.BaseFragment
import com.example.speedrun.ui.main.MainFragmentCommunicator
import com.example.speedrun.utils.ItemDivideDecorator
import com.google.android.material.transition.MaterialFadeThrough
import kotlinx.android.synthetic.main.fragment_latest_runs.*

class LatestRunsFragment : BaseFragment() {

    companion object {
        fun newInstance() = LatestRunsFragment()
    }

    var viewModel: LatestRunsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = MaterialFadeThrough.create()
        exitTransition = MaterialFadeThrough.create()
    }

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

        viewModel?.latestRunsLiveData?.observe(this, Observer { runs ->
            if (runs.isNullOrEmpty()) {
                return@Observer
            }

            main_rv_latest_runs.adapter = LatestGameAdapter(viewModel, runs)
            main_rv_latest_runs.isNestedScrollingEnabled = false
        })

        viewModel?.latestUserPressedLiveData?.observe(this, Observer { userId ->
            if (userId.isNullOrEmpty()) {
                return@Observer
            }

            (activity as MainFragmentCommunicator).onPlayerClicked(userId)
        })

        viewModel?.latestGamePressedLiveData?.observe(this, Observer { gameId ->
            if (gameId.isNullOrEmpty()) {
                return@Observer
            }

            (activity as MainFragmentCommunicator).onGameClicked(gameId)
        })

        viewModel?.latestRunPressedLiveData?.observe(this, Observer {runId ->
            if (runId.isNullOrEmpty()) {
                return@Observer
            }

            (activity as MainFragmentCommunicator).onRunClicked(runId)
        })

    }

}