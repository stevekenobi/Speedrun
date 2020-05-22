package com.example.speedrun.ui.main.series

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.DataManager
import com.example.network.model.dto.SeriesDto
import com.example.speedrun.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SeriesViewModel @Inject constructor(val dataManager: DataManager) : BaseViewModel() {
    val seriesLiveData = MutableLiveData<List<SeriesDto>>()
    val seriesClickedLiveData = MutableLiveData<String>()

    fun getSeries() {
        viewModelScope.launch(Dispatchers.IO) {
            val series = dataManager.speedrunDataManager.getSeries()

            seriesLiveData.postValue(series)
        }
    }
}