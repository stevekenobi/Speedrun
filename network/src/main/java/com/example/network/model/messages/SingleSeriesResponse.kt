package com.example.network.model.messages

import com.example.network.model.dto.SeriesDto

data class SingleSeriesResponse(val data: SeriesDto) : BaseResponse()