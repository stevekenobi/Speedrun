package com.example.speedrun.model

import com.example.network.model.dto.UserRunDto

data class UserGameModel(
    val id: String,
    val name: String?,
    val image: String?,
    val imageWidth: Int?,
    val imageHeight: Int?,
    val runs: MutableList<UserRunDto>,
    val showMills: Boolean,
    val timeToShow: String
)