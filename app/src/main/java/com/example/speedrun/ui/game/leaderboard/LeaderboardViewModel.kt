package com.example.speedrun.ui.game.leaderboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.DataManager
import com.example.network.model.dto.LeaderboardRunDto
import com.example.speedrun.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LeaderboardViewModel @Inject constructor(val dataManager: DataManager) : BaseViewModel() {

    val leaderboardLiveData = MutableLiveData<List<LeaderboardRunDto>>()
    val leaderboardRunClickedLiveData = MutableLiveData<String>()
    val leaderboardUserClickedLiveData = MutableLiveData<String>()

    fun getLeaderboard(gameId: String?, categoryId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                isLoadingLiveData.postValue(true)
                if (gameId.isNullOrEmpty() || categoryId.isNullOrEmpty())
                    return@launch

                val result = dataManager.speedrunDataManager.getCategoryLeaderboard(gameId, categoryId)

                leaderboardLiveData.postValue(result)
            } catch (e: Exception) {
                handleException(e)
            } finally {
                isLoadingLiveData.postValue(false)
            }
        }
    }

    fun getLevelLeaderboard(gameId: String?, levelId: String, categoryId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                isLoadingLiveData.postValue(true)
                if (gameId.isNullOrEmpty() || categoryId.isNullOrEmpty())
                    return@launch

                val result = dataManager.speedrunDataManager.getLevelLeaderboard(gameId, levelId, categoryId)

                leaderboardLiveData.postValue(result)
            } catch (e: Exception) {
                handleException(e)
            } finally {
                isLoadingLiveData.postValue(false)
            }
        }
    }
}