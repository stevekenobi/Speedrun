package com.example.speedrun.ui.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Datamanager
import com.example.network.model.dto.GameDetailsDto
import com.example.network.model.dto.LeaderboardRunDto
import com.example.speedrun.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameDetailsViewModel @Inject constructor(val datamanager: Datamanager) : BaseViewModel() {

    val gameDetailsLiveData = MutableLiveData<GameDetailsDto>()
    val leaderboardsLiveData = MutableLiveData<List<List<LeaderboardRunDto>>>()

    fun getGameDetails(gameId: String?) {
        if (gameId.isNullOrEmpty())
            return

        viewModelScope.launch(Dispatchers.IO) {
            val gameDetails = datamanager.getGameDetails(gameId)
            gameDetailsLiveData.postValue(gameDetails)
        }
    }

    fun getLeaderboards(gameDetails: GameDetailsDto) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = arrayListOf<List<LeaderboardRunDto>>()

            gameDetails.categories.data.forEach {
                if (it.type == "per-level")
                    return@forEach

                val leaderboardForCategory = datamanager.getCategoryLeaderboard(gameDetails.id, it.id)

                result.add(leaderboardForCategory)
            }

            leaderboardsLiveData.postValue(result)
        }
    }

}