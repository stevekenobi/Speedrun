package com.example.speedrun.ui.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Datamanager
import com.example.network.model.dto.CategoryDto
import com.example.speedrun.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameLeaderboardViewModel @Inject constructor(val datamanager: Datamanager) : BaseViewModel() {

    val categoriesLiveData = MutableLiveData<List<CategoryDto>>()

    fun getCategories(gameId: String?, levelId: String?) {
        if (gameId.isNullOrEmpty())
            return

        viewModelScope.launch(Dispatchers.IO) {
            if (levelId.isNullOrEmpty()) {
                val categories = datamanager.getCategories(gameId)

                categoriesLiveData.postValue(categories)
            } else {
                val categories =  datamanager.getLevelCategories(levelId)

                categoriesLiveData.postValue(categories)
            }
        }
    }

}