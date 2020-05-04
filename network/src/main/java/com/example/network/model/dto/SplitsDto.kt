package com.example.network.model.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SplitsDto(
    val rel: String,
    val uri: String
): Parcelable