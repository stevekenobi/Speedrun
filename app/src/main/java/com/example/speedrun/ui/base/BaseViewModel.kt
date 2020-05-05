package com.example.speedrun.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.IOException

open class BaseViewModel : ViewModel() {

    val isLoadingLiveData = MutableLiveData<Boolean>()

    val serverErrorLiveData = MutableLiveData<Boolean>()

    val noInternetConnectionLiveData = MutableLiveData<Boolean>()

    fun handleException(t: Throwable) {
        t.printStackTrace()

        when (t) {
            is IOException -> noInternetConnectionLiveData.postValue(true)
            is java.net.UnknownHostException -> serverErrorLiveData.postValue(true)
        }
    }
}