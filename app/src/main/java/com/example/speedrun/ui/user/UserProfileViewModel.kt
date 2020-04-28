package com.example.speedrun.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Datamanager
import com.example.network.model.dto.UserDto
import com.example.network.model.dto.UserRunDto
import com.example.speedrun.model.UserGameModel
import com.example.speedrun.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(val datamanager: Datamanager) : BaseViewModel() {

    val userDetailsLiveData = MutableLiveData<UserDto>()
    val userRunsLiveData = MutableLiveData<List<UserGameModel>>()

    fun getUserDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoadingLiveData.postValue(true)

            val userDetails = datamanager.getUserDetails(id)
            userDetailsLiveData.postValue(userDetails)

            val userRuns = datamanager.getUserRuns(id)
            val result = mutableListOf<UserGameModel>()

            userRuns.forEach run@ {run: UserRunDto ->
                result.forEach game@ {game: UserGameModel ->
                    if (run.game.data.id == game.id) {
                        game.runs.add(run)
                        return@run
                    }
                }
                val newGame = run.game.data
                result.add(UserGameModel(newGame.id, newGame.names?.international, newGame.assets.coverSmall?.uri, mutableListOf(run)))
            }

            userRunsLiveData.postValue(result)

            isLoadingLiveData.postValue(false)
        }
    }
}