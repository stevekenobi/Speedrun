package com.example.network.model.dto

import android.os.Parcelable
import com.example.network.model.Link
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideosDto(
    val links: List<Link>
) : Parcelable