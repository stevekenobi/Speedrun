package com.example.network.model.messages

import com.example.network.model.dto.GameDto

data class GameResponse(val data: GameDto) : BaseResponse()