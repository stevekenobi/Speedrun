package com.example.network.model.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeaderboardRunDto(
    val place: Int?,
    val run: RunForLeaderboardDto
): Parcelable