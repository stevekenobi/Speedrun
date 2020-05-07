package com.example.network.model.messages

import com.example.network.model.dto.LatestRunDto

data class LatestRunResponse(val data: List<LatestRunDto>) : BaseResponse()