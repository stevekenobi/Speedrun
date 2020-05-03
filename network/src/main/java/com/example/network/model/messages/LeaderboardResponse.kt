package com.example.network.model.messages

import com.example.network.model.dto.LeaderboardDto

data class LeaderboardResponse(val data: LeaderboardDto) : BaseResponse()