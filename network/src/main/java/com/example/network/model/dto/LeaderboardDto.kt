package com.example.network.model.dto

import com.example.network.model.messages.PlayersResponse
import com.google.gson.annotations.SerializedName

data class LeaderboardDto(
    val weblink: String,
    val game: String,
    val category: String,
    val level: String?,
    val region: RegionDto?,
    val emulators: List<String>?,
    @SerializedName("video-only")val videoOnly: Boolean,
    val timing: String,
    val values: Any?,
    val runs: List<LeaderboardRunDto>,
    val players: PlayersResponse
)