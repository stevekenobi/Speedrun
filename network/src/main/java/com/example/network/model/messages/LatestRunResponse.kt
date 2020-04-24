package com.example.network.model.messages

import com.example.network.model.dto.LatestRunDto

class LatestRunResponse(val data: List<LatestRunDto>) : BaseResponse()