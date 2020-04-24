package com.example.speedrun.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Datamanager
import com.example.network.model.dto.LatestRunDto
import com.example.speedrun.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val datamanager: Datamanager) : BaseViewModel() {
    val latestRunsLiveData = MutableLiveData<List<LatestRunDto>>()

    fun getLatestRuns() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoadingLiveData.postValue(true)
            val runs = datamanager.getLatestRuns()
            latestRunsLiveData.postValue(runs)
            isLoadingLiveData.postValue(false)
        }
    }
}