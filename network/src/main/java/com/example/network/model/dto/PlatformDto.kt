package com.example.network.model.dto

import com.example.network.model.Link

data class PlatformDto(
    val id: String,
    val name: String,
    val released: Int,
    val links: List<Link>
)