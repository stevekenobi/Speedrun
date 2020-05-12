package com.example.network.model.dto

import com.example.network.model.Link
import com.example.network.model.messages.CategoriesResponse

data class LevelDto(
    val id: String,
    val name: String,
    val weblink: String,
    val rules: Any?,
    val categories: CategoriesResponse,
    val links: List<Link>
)