package com.example.storage.interfaces

interface AuthorizationStorageManager {
    fun getUserId(): Int

    fun setUserId(id: Int)

    fun getTokenType(): String?

    fun setTokenType(tokenType: String?)

    fun getAccessToken(): String?

    fun setAccessToken(accessToken: String?)

    fun getRefreshToken(): String?

    fun setRefreshToken(refreshToken: String?)

    fun getExpirationDate(): Long

    fun setExpirationDate(expirationDate: Long)
}