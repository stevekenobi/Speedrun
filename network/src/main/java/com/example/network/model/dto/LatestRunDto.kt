package com.example.network.model.dto

import com.example.network.model.Link
import com.example.network.model.messages.CategoryResponse
import com.example.network.model.messages.GameResponse
import com.example.network.model.messages.PlayersResponse

data class LatestRunDto(
    val id: String,
    val weblink: String,
    val game: GameResponse,
    val level: String?,
    val category: CategoryResponse,
    val videos: VideosDto,
    val comment: String?,
    val status: StatusDto,
    val players: PlayersResponse,
    val date: String,
    val submitted: String?,
    val times: TimeDto,
    val system: SystemDto,
    val splits: SplitsDto?,
    val values: Any,
    val links: List<Link>
)