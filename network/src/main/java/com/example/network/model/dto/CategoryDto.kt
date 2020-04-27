package com.example.network.model.dto

import com.example.network.model.Link

data class CategoryDto(
    val id: String,
    val name: String,
    val weblink: String,
    val type: String,
    val rules: String,
    val players: PlayerPerCategoryDto,
    val miscellaneous: Boolean,
    val links: List<Link>
)