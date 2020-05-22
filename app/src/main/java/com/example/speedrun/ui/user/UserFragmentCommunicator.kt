package com.example.speedrun.ui.user

interface UserFragmentCommunicator {
    fun onGameClicked(id: String)

    fun onSeriesClicked(id: String)

    fun onRunClicked(id: String)
}