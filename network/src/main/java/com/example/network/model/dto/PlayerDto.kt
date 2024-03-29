package com.example.network.model.dto


data class PlayerDto(
    val rel: String,
    val id: String,
    val uri: String,
    val name: String? // Only for rel == "guest"
)