package com.example.speedrun.injection.modules

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.speedrun.injection.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    fun provideFragment(): Fragment {
        return fragment
    }

    @Provides
    @ActivityContext
    fun provideContext(): Context {
        return fragment.context!!
    }
}