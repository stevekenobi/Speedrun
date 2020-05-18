package com.example.network.model.dto

import com.example.network.model.Link
import com.google.gson.annotations.SerializedName

data class GameDto(
    val id: String,
    val names: NamesDto?,
    val abbreviation: String,
    val weblink: String,
    val released: Int,
    @SerializedName("release-date") val releaseDate: String,
    val ruleset: RulesetDto,
    val romhack: Boolean,
    val gameTypes: List<String>,
    val platforms: List<String>,
    val regions: List<String>,
    val genres: List<String>,
    val engines: List<String>,
    val developers: List<String>,
    val assets: AssetDto,
    val publishers: List<String>,
    val moderators: Any,
    val created: String,
    val links: List<Link>
)