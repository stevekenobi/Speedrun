package com.example.speedrun.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.DataManager
import com.example.network.model.dto.UserDto
import com.example.speedrun.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserMenuViewModel @Inject constructor(val datamanager: DataManager): BaseViewModel() {
    val userDetailsLiveData = MutableLiveData<UserDto>()

    fun getUserDetails(id: String?) {
        if (id.isNullOrEmpty())
            return

        viewModelScope.launch(Dispatchers.IO) {
            val userDetails = datamanager.speedrunDataManager.getUserDetails(id)
            userDetailsLiveData.postValue(userDetails)
        }
    }
}