package com.example.storage

import android.content.Context
import android.content.SharedPreferences
import com.example.storage.interfaces.AuthorizationStorageManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesManager @Inject constructor(private val context: Context) : AuthorizationStorageManager {

    private val mPref: SharedPreferences

    init {
        mPref =context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }
    override fun getUserId(): Int {
        return mPref.getInt(KEY_USER_ID, 0)
    }

    override fun setUserId(id: Int) {
        mPref.edit().putInt(KEY_USER_ID, id).apply()
    }
    override fun getTokenType(): String? {
        return mPref.getString(KEY_TOKEN_TYPE, null)
    }

    override fun setTokenType(tokenType: String?) {
        mPref.edit().putString(KEY_TOKEN_TYPE, tokenType).apply()
    }

    override fun getAccessToken(): String? {
        return mPref.getString(KEY_ACCESS_TOKEN, null)
    }

    override fun setAccessToken(accessToken: String?) {
        mPref.edit().putString(KEY_ACCESS_TOKEN, accessToken).apply()
    }

    override fun getRefreshToken(): String? {
        return mPref.getString(KEY_REFRESH_TOKEN, null)
    }

    override fun setRefreshToken(refreshToken: String?) {
        mPref.edit().putString(KEY_REFRESH_TOKEN, refreshToken).apply()
    }

    override fun getExpirationDate(): Long {
        return mPref.getLong(KEY_EXPIRATION_DATE, -1)
    }

    override fun setExpirationDate(expirationDate: Long) {
        mPref.edit().putLong(KEY_EXPIRATION_DATE, expirationDate).apply()
    }

    companion object {
        private const val PREF_FILE_NAME = "android_boilerplate_pref_file"


        private const val KEY_USER_ID = "USER_ID"
        private const val KEY_TOKEN_TYPE = "TOKEN_TYPE"
        private const val KEY_ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val KEY_REFRESH_TOKEN = "REFRESH_TOKEN"
        private const val KEY_EXPIRATION_DATE = "EXPIRATION_DATE"
    }
}