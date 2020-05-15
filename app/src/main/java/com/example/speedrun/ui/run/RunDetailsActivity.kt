package com.example.speedrun.ui.run

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.network.model.dto.RunDto
import com.example.network.model.splits.SplitsDto
import com.example.speedrun.R
import com.example.speedrun.ui.base.BaseActivity
import com.example.speedrun.utils.ActivityExtras
import com.example.speedrun.utils.RunTimeConverter
import com.example.speedrun.utils.RunsTextUtils
import kotlinx.android.synthetic.main.activity_run_details.*

class RunDetailsActivity : BaseActivity() {

    var viewModel: RunDetailsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_run_details)

        activityComponent?.inject(this)

        initUi()

        viewModel?.getRunDetails(intent.getStringExtra(ActivityExtras.EXTRA_RUN_ID))
    }

    private fun initUi() {
        run_splits.layoutManager = LinearLayoutManager(this)
    }

    private fun createSplits(splits: List<SplitsDto>) {
        run_splits.adapter = SplitsAdapter(splits)
    }

    override fun initViewModel() {
        viewModel = viewModelFactory.create(RunDetailsViewModel::class.java)
    }

    override fun observeViewModel() {
        viewModel?.runDetailsLiveData?.observe(this, Observer {
            setDetailsPage(it)
        })

        viewModel?.splitsLiveData?.observe(this, Observer {
            if (it.isNullOrEmpty())
                return@Observer

            createSplits(it)
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setDetailsPage(run: RunDto) {
        run_details.text =
            "${run.category.data.name} in ${RunTimeConverter.from(run.times.primary_t)} by ${RunsTextUtils.setPlayersText(
                run.players.data
            )}"
        run_comment.text = run.comment
    }
}