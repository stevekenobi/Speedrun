package com.example.network.model

data class Pagination(
    val offset: Int,
    val max: Int,
    val size: Int,
    val links: List<Link>
)