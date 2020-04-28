package com.example.speedrun.model

import com.example.network.model.dto.LatestRunDto

data class LatestGameModel(
    val id: String,
    val name: String?,
    val imageURI: String?,
    val runs: MutableList<LatestRunDto>
)