package com.example.network.model.messages

import com.example.network.model.dto.DeveloperDto

data class DevelopersResponse(val data: List<DeveloperDto>): BaseResponse()