package com.example.speedrun.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.Datamanager
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    val isLoadingLiveData = MutableLiveData<Boolean>()

    val serverErrorLiveDate = MutableLiveData<Boolean>()
}