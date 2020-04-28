package com.example.network.model.dto

import com.example.network.model.Link
import com.google.gson.annotations.SerializedName

data class PlayerDto(
    val rel: String,
    val id: String,
    val names: NamesDto,
    val weblink: String,
    @SerializedName("name-style")val nameStyle: StyleDto,
    val role: String,
    val signup: String,
    val location: LocationDto,
    val twitch: Link?,
    val hitbox: Link?,
    val twitter: Link?,
    val speedrunslive: Link?,
    val links: List<Link>?
)