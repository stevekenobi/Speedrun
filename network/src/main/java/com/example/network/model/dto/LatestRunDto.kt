package com.example.network.model.dto

import java.util.*

data class LatestRunDto(
    val id: Int,
    val gameName: String,
    val categoryName: String,
    val dateCompleted: Date,
    val timeCompleted: Long,
    val username: String
)