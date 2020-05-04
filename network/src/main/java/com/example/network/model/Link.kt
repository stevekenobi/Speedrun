package com.example.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Link(
    val rel: String?,
    val uri: String?
) : Parcelable