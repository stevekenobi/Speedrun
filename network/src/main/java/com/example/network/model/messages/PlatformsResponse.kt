package com.example.network.model.messages

import com.example.network.model.dto.PlatformDto

data class PlatformsResponse(val data: List<PlatformDto>) : BaseResponse()