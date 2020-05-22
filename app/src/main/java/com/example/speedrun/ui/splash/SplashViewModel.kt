package com.example.speedrun.ui.splash

import androidx.lifecycle.viewModelScope
import com.example.data.DataManager
import com.example.speedrun.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val datamanager: DataManager): BaseViewModel() {

    fun updateResources() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoadingLiveData.postValue(true)

//            val updatedMap = datamanager.getResources()
//            Restring.setStrings(Locale.getDefault().language, updatedMap)

            isLoadingLiveData.postValue(false)
        }
    }
}