package com.example.network.model.dto

import com.google.gson.annotations.SerializedName


data class StatusDto(
    val status: String,
    val examiner: String?,
    @SerializedName("verify-date")
    val verifyDate: String?,
    val reason: String?
)