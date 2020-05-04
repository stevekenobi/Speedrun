package com.example.network.model.dto

import com.example.network.model.Link
import com.example.network.model.messages.CategoryResponse
import com.example.network.model.messages.LevelResponse
import com.example.network.model.messages.PlayersResponse

data class RunDto(
    val id: String,
    val weblink: String,
    val game: String,
    val category: CategoryResponse,
    val level: String?,
    val videos: VideosDto,
    val comment: String?,
    val status: StatusDto,
    val players: PlayersResponse,
    val date: String,
    val submitted: String,
    val times: TimeDto,
    val system: SystemDto,
    val splits: SplitsDto?,
    val links: List<Link>
)