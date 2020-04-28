package com.example.speedrun.model

import com.example.network.model.dto.UserRunDto

data class UserGameModel(
    val id: String,
    val name: String?,
    val image: String?,
    val runs: MutableList<UserRunDto>
)