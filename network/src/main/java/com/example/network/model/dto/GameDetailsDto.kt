package com.example.network.model.dto

import com.example.network.model.Link
import com.example.network.model.messages.*

data class GameDetailsDto(
    val id: String,
    val names: NamesDto?,
    val abbreviation: String,
    val weblink: String,
    val released: Int,
    val releaseDate: String,
    val ruleset: Any,
    val romhack: Boolean,
    val gameTypes: List<String>,
    val platforms: PlatformsResponse,
    val regions: List<String>,
    val genres: List<String>,
    val engines: List<String>,
    val developers: DevelopersResponse,
    val assets: AssetDto,
    val publishers: List<String>,
    val moderators: PlayersResponse,
    val created: String,
    val links: List<Link>,
    val categories: CategoriesResponse,
    val levels: LevelsResponse
)