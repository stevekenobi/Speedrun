package com.example.speedrun.ui.splash

import android.os.Bundle
import android.view.View
import com.example.speedrun.R
import com.example.speedrun.ui.base.BaseActivity
import com.example.speedrun.viewmodel.SpeedrunViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: SpeedrunViewModelFactory

    private var viewModel: SplashViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityComponent?.inject(this)

        initViewModel()

        viewModel?.updateResources()
    }

    private fun initViewModel() {
        viewModel = viewModelFactory.create(SplashViewModel::class.java)

        viewModel?.isLoadingLiveData?.observe(this, androidx.lifecycle.Observer {
            if (it == null)
                return@Observer

            if (it){
                splash_loader.visibility = View.VISIBLE
            } else {
                splash_loader.visibility = View.GONE
            }
        })
    }

}