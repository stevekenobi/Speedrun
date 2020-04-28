package com.example.network.model.dto

import com.example.network.model.Link

data class LevelDto(
    val id: String,
    val name: String,
    val weblink: String,
    val rules: Any,
    val links: List<Link>
)