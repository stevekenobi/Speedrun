package com.example.speedrun.ui.run

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Datamanager
import com.example.network.model.dto.RunDto
import com.example.speedrun.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RunDetailsViewModel @Inject constructor(val datamanager: Datamanager) : BaseViewModel() {
    val runDetailsLiveData = MutableLiveData<RunDto>()

    fun getRunDetails(runId: String?) {
        if (runId.isNullOrEmpty())
            return

        viewModelScope.launch(Dispatchers.IO) {
            val runDto = datamanager.getRunDetails(runId)

            runDetailsLiveData.postValue(runDto)
        }
    }
}