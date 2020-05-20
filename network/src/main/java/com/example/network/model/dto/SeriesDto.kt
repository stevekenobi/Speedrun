package com.example.network.model.dto

import com.example.network.model.Link

data class SeriesDto(
    val id: String,
    val names: NamesDto?,
    val abbreviation: String,
    val weblink: String,
    val moderators: Map<String, String>,
    val created: String,
    val assets: AssetDto,
    val links: List<Link>
    )