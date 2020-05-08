package com.example.network.model.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatusDto(
    val status: String,
    val examiner: String?,
    @SerializedName("verify-date")
    val verifyDate: String?,
    val reason: String?
) : Parcelable