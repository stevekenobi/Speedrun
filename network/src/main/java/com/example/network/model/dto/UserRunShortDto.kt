package com.example.network.model.dto

import com.example.network.model.Link

data class UserRunShortDto(
    val id: String,
    val weblink: String,
    val game: String,
    val level: String?,
    val category: String,
    val videos: VideosDto,
    val comment: String,
    val status: StatusDto,
    val players: List<PlayerDto>,
    val date: String,
    val submitted: String,
    val times: TimeDto,
    val system: SystemDto,
    val values: Any?,
    val links: List<Link>
)