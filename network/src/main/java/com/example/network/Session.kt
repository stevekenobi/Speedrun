package com.example.network

import com.example.network.model.messages.AuthenticationResponse

interface Session {
    fun setUserId(userId: Int)

    fun userId(): Int

    fun updateExpirationDate(expiresIn: Long)

    val isLoggedIn: Boolean

    fun authorizationHeader(): String?

    fun setTokenType(tokenType: String?)

    fun tokenType(): String?

    fun setAccessToken(token: String?)

    fun accessToken(): String?

    fun setRefreshToken(refreshToken: String?)

    fun refreshToken(): String?

    fun updateAuthenticationResponseData(authenticationResponse: AuthenticationResponse?)

    fun logout()
}