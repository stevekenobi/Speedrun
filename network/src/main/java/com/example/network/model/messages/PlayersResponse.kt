package com.example.network.model.messages

import com.example.network.model.dto.UserDto

data class PlayersResponse(val data: List<UserDto>): BaseResponse()