package com.example.speedrun.ui.main.popular

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Datamanager
import com.example.network.model.dto.GameDto
import com.example.speedrun.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularGamesViewModel @Inject constructor(val datamanager: Datamanager): BaseViewModel() {

    val gamesLiveData = MutableLiveData<List<GameDto>>()
    val gameClickedLiveData = MutableLiveData<String>()

    fun getGames() {
        viewModelScope.launch(Dispatchers.IO) {
            val games = datamanager.getRecentGames()

            gamesLiveData.postValue(games)
        }
    }
}