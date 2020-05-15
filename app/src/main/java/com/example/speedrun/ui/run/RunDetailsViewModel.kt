package com.example.speedrun.ui.run

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Datamanager
import com.example.network.model.dto.RunDto
import com.example.network.model.splits.SplitsDto
import com.example.speedrun.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RunDetailsViewModel @Inject constructor(val datamanager: Datamanager) : BaseViewModel() {
    val runDetailsLiveData = MutableLiveData<RunDto>()
    val splitsLiveData = MutableLiveData<List<SplitsDto>>()

    fun getRunDetails(runId: String?) {
        if (runId.isNullOrEmpty())
            return

        viewModelScope.launch(Dispatchers.IO) {
            val runDto = datamanager.getRunDetails(runId)

            val splitId = runDto.splits?.uri?.split("/runs/")?.get(1)

            getSplits(splitId)

            runDetailsLiveData.postValue(runDto)
        }
    }

    fun getSplits(id: String?) {
        if (id.isNullOrEmpty())
            return

        viewModelScope.launch(Dispatchers.IO) {
            val splits = datamanager.getSplitsForRun(id)

            splitsLiveData.postValue(splits)
        }
    }
}