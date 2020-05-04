package com.example.network.model.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SystemDto(
    val platform: String,
    val emulated: Boolean,
    val region: String
) : Parcelable