package com.example.speedrun.ui.main.popular

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.DataManager
import com.example.network.model.dto.GameDto
import com.example.speedrun.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularGamesViewModel @Inject constructor(val datamanager: DataManager): BaseViewModel() {

    val gamesLiveData = MutableLiveData<List<GameDto>>()
    val gameClickedLiveData = MutableLiveData<String>()

    fun getGames(offset: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val games = datamanager.speedrunDataManager.getRecentGames(offset)

            gamesLiveData.postValue(games)
        }
    }
}