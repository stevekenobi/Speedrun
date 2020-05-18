package com.example.speedrun.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Datamanager
import com.example.network.model.dto.LatestRunDto
import com.example.speedrun.model.LatestGameModel
import com.example.speedrun.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LatestRunsViewModel @Inject constructor(val dataManager: Datamanager): BaseViewModel() {
    val latestRunsLiveData = MutableLiveData<List<LatestGameModel>>()

    val latestUserPressedLiveData = MutableLiveData<String>()
    val latestGamePressedLiveData = MutableLiveData<String>()
    val latestRunPressedLiveData = MutableLiveData<String>()

    fun getLatestRuns() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoadingLiveData.postValue(true)
                val runs = dataManager.getLatestRuns()

                val result = mutableListOf<LatestGameModel>()

                runs.forEach run@{ run: LatestRunDto ->
                    result.forEach game@{ game: LatestGameModel ->
                        if (run.game.data.id == game.id) {
                            game.runs.add(run)
                            return@run
                        }
                    }
                    val newGame = run.game.data
                    result.add(
                        LatestGameModel(
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

                latestRunsLiveData.postValue(result)
            } catch (e: Exception) {
                handleException(e)
            } finally {
                isLoadingLiveData.postValue(false)
            }
        }
    }
}