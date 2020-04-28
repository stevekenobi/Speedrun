package com.example.network.model.messages

import com.example.network.model.dto.LevelDto

data class LevelResponse(val data: List<LevelDto>) : BaseResponse()