package com.example.network.model.dto

import com.example.network.model.Link

data class DeveloperDto(
    val id: String,
    val name: String,
    val links: List<Link>
)