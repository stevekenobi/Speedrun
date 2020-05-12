package com.example.speedrun.ui.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Datamanager
import com.example.network.model.dto.CategoryDto
import com.example.network.model.dto.LevelDto
import com.example.speedrun.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameLeaderboardViewModel @Inject constructor(val datamanager: Datamanager): BaseViewModel() {

    val categoriesLiveData = MutableLiveData<List<CategoryDto>>()
    val levelsLiveData = MutableLiveData<List<LevelDto>>()
    val levelSelectedLiveData = MutableLiveData<String>()

    fun getCategories(gameId: String?) {
        if (gameId.isNullOrEmpty())
            return

        viewModelScope.launch(Dispatchers.IO) {
            val categories = datamanager.getCategories(gameId)

            categoriesLiveData.postValue(categories)
        }
    }

    fun getLevels(gameId: String?) {
        if (gameId.isNullOrEmpty())
            return

        viewModelScope.launch(Dispatchers.IO) {
            val levels = datamanager.getLevels(gameId)

            levelsLiveData.postValue(levels)
        }
    }
}