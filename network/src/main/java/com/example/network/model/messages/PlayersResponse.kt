package com.example.network.model.messages

import com.example.network.model.dto.PlayerDto

data class PlayersResponse(val data: List<PlayerDto>): BaseResponse()