package com.example.speedrun.ui.splash

import androidx.lifecycle.viewModelScope
import com.example.data.Datamanager
import com.example.speedrun.ui.base.BaseViewModel
import com.ice.restring.Restring
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val datamanager: Datamanager): BaseViewModel() {

    fun updateResources() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoadingLiveData.postValue(true)

            val updatedMap = datamanager.getResources()
            Restring.setStrings(Locale.getDefault().language, updatedMap)

            isLoadingLiveData.postValue(false)
        }
    }
}