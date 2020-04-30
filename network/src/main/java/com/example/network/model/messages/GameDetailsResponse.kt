package com.example.network.model.messages

import com.example.network.model.dto.GameDetailsDto

data class GameDetailsResponse(val data: GameDetailsDto) : BaseResponse()