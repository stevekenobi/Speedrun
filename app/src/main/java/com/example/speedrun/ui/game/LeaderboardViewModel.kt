package com.example.speedrun.ui.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Datamanager
import com.example.network.model.dto.LeaderboardRunDto
import com.example.speedrun.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LeaderboardViewModel @Inject constructor(val dataManager: Datamanager) : BaseViewModel() {

    val leaderboardLiveData = MutableLiveData<List<LeaderboardRunDto>>()

    fun getLeaderboard(gameId: String?, categoryId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (gameId.isNullOrEmpty() || categoryId.isNullOrEmpty())
                return@launch

            val result = dataManager.getCategoryLeaderboard(gameId, categoryId)

            leaderboardLiveData.postValue(result)
        }
    }
}