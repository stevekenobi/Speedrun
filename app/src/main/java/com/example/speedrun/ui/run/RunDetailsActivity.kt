package com.example.speedrun.ui.run

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.network.model.dto.RunDto
import com.example.speedrun.R
import com.example.speedrun.ui.base.BaseActivity
import com.example.speedrun.utils.ActivityExtras
import com.example.speedrun.utils.RunTimeConverter
import com.example.speedrun.viewmodel.SpeedrunViewModelFactory
import kotlinx.android.synthetic.main.activity_run_details.*
import javax.inject.Inject

class RunDetailsActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: SpeedrunViewModelFactory

    var viewModel: RunDetailsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_run_details)

        activityComponent?.inject(this)

        initViewModel()

        viewModel?.getRunDetails(intent.getStringExtra(ActivityExtras.EXTRA_RUN_ID))
    }

    private fun initViewModel() {
        viewModel = viewModelFactory.create(RunDetailsViewModel::class.java)

        viewModel?.runDetailsLiveData?.observe(this, Observer {
            setDetailsPage(it)
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setDetailsPage(run: RunDto) {
        run_details.text = "${run.category.data.name} in ${RunTimeConverter.from(run.times.primary_t)} by ${run.players.data[0].names?.international}"
        run_comment.text = run.comment
    }
}