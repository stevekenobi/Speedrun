package com.example.network.model.messages

import com.example.network.model.dto.GameDto

data class GamesResponse(val data: List<GameDto>) : BaseResponse()