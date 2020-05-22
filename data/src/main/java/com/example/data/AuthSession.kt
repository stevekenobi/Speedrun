package com.example.data

import com.example.network.Session
import com.example.network.model.messages.AuthenticationResponse
import com.example.storage.interfaces.AuthorizationStorageManager
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthSession @Inject constructor(private val authorizationStorageManager: AuthorizationStorageManager) : Session {
    override fun updateExpirationDate(expiresIn: Long) {
        authorizationStorageManager.setExpirationDate(Date().time + expiresIn)
    }

    override val isLoggedIn: Boolean
        get() {
            val hasAccessToken = authorizationStorageManager.getAccessToken() != null
            val hasRefreshToken = authorizationStorageManager.getRefreshToken() != null
            return hasAccessToken && hasRefreshToken
        }

    override fun setUserId(userId: Int) {
        authorizationStorageManager.setUserId(userId)
    }

    override fun userId(): Int {
        return authorizationStorageManager.getUserId()
    }
    override fun setTokenType(tokenType: String?) {
        authorizationStorageManager.setTokenType(tokenType)
    }

    override fun tokenType(): String {
        return authorizationStorageManager.getTokenType()!!
    }

    override fun setAccessToken(token: String?) {
        authorizationStorageManager.setAccessToken(token)
    }

    override fun accessToken(): String {
        return authorizationStorageManager.getAccessToken()!!
    }

    override fun setRefreshToken(refreshToken: String?) {
        authorizationStorageManager.setRefreshToken(refreshToken)
    }

    override fun refreshToken(): String {
        return authorizationStorageManager.getRefreshToken()!!
    }

    override fun authorizationHeader(): String? {
        return if (isLoggedIn) {
            String.format("%s %s",
                tokenType(),
                accessToken())
        } else null
    }

    override fun updateAuthenticationResponseData(authenticationResponse: AuthenticationResponse?) {
        setTokenType(authenticationResponse?.tokenType)
        setAccessToken(authenticationResponse?.accessToken)
        setRefreshToken(authenticationResponse?.refreshToken)
        authenticationResponse?.let{ response ->
            updateExpirationDate(response.expiresIn)
        }
    }

    override fun logout() {
        setTokenType(null)
        setAccessToken(null)
        setRefreshToken(null)
        updateExpirationDate(0)
    }
}