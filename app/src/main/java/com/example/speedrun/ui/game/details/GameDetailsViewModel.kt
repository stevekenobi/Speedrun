package com.example.speedrun.ui.game.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Datamanager
import com.example.network.model.dto.GameDetailsDto
import com.example.speedrun.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameDetailsViewModel @Inject constructor(val datamanager: Datamanager) : BaseViewModel() {

    val gameDetailsLiveData = MutableLiveData<GameDetailsDto>()
    val levelSelectedLiveData = MutableLiveData<String>()

    fun getGameDetails(gameId: String?) {
        if (gameId.isNullOrEmpty())
            return

        viewModelScope.launch(Dispatchers.IO) {
            val gameDetails = datamanager.getGameDetails(gameId)
            gameDetailsLiveData.postValue(gameDetails)
        }
    }


}