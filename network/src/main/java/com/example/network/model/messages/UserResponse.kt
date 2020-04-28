package com.example.network.model.messages

import com.example.network.model.dto.UserDto

data class UserResponse(val data: UserDto) : BaseResponse()