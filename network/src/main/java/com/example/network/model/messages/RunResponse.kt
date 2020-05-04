package com.example.network.model.messages

import com.example.network.model.dto.RunDto

data class RunResponse(val data: RunDto) : BaseResponse()