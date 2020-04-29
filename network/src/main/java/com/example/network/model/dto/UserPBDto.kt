package com.example.network.model.dto

import com.example.network.model.messages.PlayersResponse

data class UserPBDto(
    val place: Int,
    val run: UserRunShortDto,
    val players: PlayersResponse
)