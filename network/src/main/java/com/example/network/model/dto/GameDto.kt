package com.example.network.model.dto

import com.example.network.model.Link

data class GameDto(
    val id: String,
    val names: NamesDto?,
    val abbeviation: String,
    val weblink: String,
    val released: Int,
    val releaseDate: String,
    val ruleset: Any,
    val romhack: Boolean,
    val gameTypes: List<String>,
    val platforms: List<String>,
    val regions: List<String>,
    val genrese: List<String>,
    val engines: List<String>,
    val developers: List<String>,
    val publishers: List<String>,
    val moderators: Any,
    val created: String,
    val links: List<Link>
)