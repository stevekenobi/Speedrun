package com.example.speedrun.ui.main

interface MainFragmentCommunicator {
    fun onPlayerClicked(id: String)

    fun onGameClicked(id: String)

    fun onRunClicked(id: String)
}