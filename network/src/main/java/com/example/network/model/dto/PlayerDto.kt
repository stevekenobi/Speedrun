package com.example.network.model.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerDto(
    val rel: String,
    val id: String,
    val uri: String
) : Parcelable