package com.example.network.model.messages

import com.example.network.model.dto.UserPBDto

data class PBResponse(val data: List<UserPBDto>) : BaseResponse()