package com.example.network.model.messages

data class AuthenticationResponse(
    val userId: Int,
    val tokenType: String,
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Long,
    val error: String?,
    val errorDescription: String?
)