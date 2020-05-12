package com.example.network.model.messages

import com.example.network.model.dto.LevelDto

data class LevelsResponse(val data: List<LevelDto>) : BaseResponse()