package com.example.speedrun.ui.user.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Datamanager
import com.example.network.model.dto.GameDto
import com.example.network.model.dto.SeriesDto
import com.example.network.model.dto.UserDto
import com.example.speedrun.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor(val datamanager: Datamanager):BaseViewModel() {
    val userDetailsLiveData = MutableLiveData<UserDto>()

    val gamesModeratedByUser = MutableLiveData<List<GameDto>>()
    val seriesModeratedByUser = MutableLiveData<List<SeriesDto>>()

    val gameClickedLiveData = MutableLiveData<String>()
    val seriesClickedLiveData = MutableLiveData<String>()

    fun getUserDetails(userId: String?) {
        if (userId.isNullOrEmpty())
            return

        viewModelScope.launch(Dispatchers.IO) {
            val user = datamanager.getUserDetails(userId)

            userDetailsLiveData.postValue(user)
        }
    }

    fun getGames(userId: String?) {
        if (userId.isNullOrEmpty())
            return

        viewModelScope.launch(Dispatchers.IO) {
            val games = datamanager.getGamesModeratedBy(userId)

            gamesModeratedByUser.postValue(games)
        }
    }

    fun getSeries(userId: String?) {
        if (userId.isNullOrEmpty())
            return

        viewModelScope.launch(Dispatchers.IO) {
            val series = datamanager.getSeriesModeratedBy(userId)

            seriesModeratedByUser.postValue(series)
        }
    }
}