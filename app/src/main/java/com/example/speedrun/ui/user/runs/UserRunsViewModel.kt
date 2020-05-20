package com.example.speedrun.ui.user.runs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Datamanager
import com.example.network.model.dto.UserRunDto
import com.example.speedrun.model.UserGameModel
import com.example.speedrun.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserRunsViewModel @Inject constructor(val datamanager: Datamanager): BaseViewModel() {

    val userRunsLiveData = MutableLiveData<List<UserGameModel>>()
    val gameClickedLiveData = MutableLiveData<String>()
    val runClickedLiveData = MutableLiveData<String>()

    fun getUserRuns(userId: String?) {
        if (userId.isNullOrEmpty())
            return

        viewModelScope.launch(Dispatchers.IO) {
            val userRuns = datamanager.getUserRuns(userId)
            val result = mutableListOf<UserGameModel>()

            userRuns.forEach run@ {run: UserRunDto ->
                result.forEach game@ {game: UserGameModel ->
                    if (run.game.data.id == game.id) {
                        game.runs.add(run)
                        return@run
                    }
                }
                val newGame = run.game.data
                result.add(
                    UserGameModel(
                        newGame.id,
                        newGame.names?.international,
                        newGame.assets.coverSmall?.uri,
                        newGame.assets.coverSmall?.width,
                        newGame.assets.coverSmall?.height,
                        mutableListOf(run),
                        newGame.ruleset.showMills,
                        newGame.ruleset.defaultTime
                    )
                )
            }

            userRunsLiveData.postValue(result)
        }
    }
}