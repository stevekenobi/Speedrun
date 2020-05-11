package com.example.speedrun.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.speedrun.R
import com.example.speedrun.ui.base.BaseActivity
import com.example.speedrun.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    private var viewModel: SplashViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        activityComponent?.inject(this)

        viewModel?.updateResources()
    }

    override fun initViewModel() {
        viewModel = viewModelFactory.create(SplashViewModel::class.java)
    }

    override fun observeViewModel() {
        viewModel?.isLoadingLiveData?.observe(this, androidx.lifecycle.Observer {
            if (it == null)
                return@Observer

            if (it){
                splash_loader.visibility = View.VISIBLE
            } else {
                splash_loader.visibility = View.GONE
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }

}