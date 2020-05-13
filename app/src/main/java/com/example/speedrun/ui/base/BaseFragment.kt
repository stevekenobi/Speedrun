package com.example.speedrun.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.speedrun.application.SpeedrunApplication
import com.example.speedrun.injection.components.DaggerFragmentComponent
import com.example.speedrun.injection.components.FragmentComponent
import com.example.speedrun.injection.modules.FragmentModule
import com.example.speedrun.viewmodel.SpeedrunViewModelFactory
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    protected lateinit var viewModelFactory: SpeedrunViewModelFactory

    lateinit var appCompatActivity: AppCompatActivity

    var fragmentComponent: FragmentComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (fragmentComponent == null) {
            val applicationComponent = (activity?.application as SpeedrunApplication).getComponent()

            fragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(applicationComponent)
                .fragmentModule(FragmentModule(this)).build()
        }

        fragmentComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initViewModel()
        observeViewModel()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is AppCompatActivity)
            appCompatActivity = activity as AppCompatActivity
    }


    protected abstract fun initViewModel()
    protected abstract fun observeViewModel()
}
