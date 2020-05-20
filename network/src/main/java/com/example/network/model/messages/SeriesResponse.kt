package com.example.network.model.messages

import com.example.network.model.dto.SeriesDto

data class SeriesResponse(val data: List<SeriesDto>) : BaseResponse()