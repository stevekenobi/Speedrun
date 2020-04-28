package com.example.network.model.dto

import com.example.network.model.Link
import com.example.network.model.messages.GameResponse
import com.example.network.model.messages.PlayersResponse

data class UserRunDto(
    val id: String,
    val weblink: String,
    val game: GameResponse,
    val level: String?,
    val category: CategoryDto,
    val videos: VideosDto,
    val comment: String,
    val status: StatusDto,
    val players: PlayersResponse,
    val date: String,
    val submitted: String,
    val times: TimeDto,
    val system: SystemDto,
    val values: Any?,
    val links: List<Link>
)