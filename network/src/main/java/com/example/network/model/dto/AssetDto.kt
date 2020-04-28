package com.example.network.model.dto

import com.google.gson.annotations.SerializedName

data class AssetDto(
    val logo: ImageDto?,
    @SerializedName("cover-tiny") val coverTiny: ImageDto?,
    @SerializedName("cover-small") val coverSmall: ImageDto?,
    @SerializedName("cover-medium") val coverMedium: ImageDto?,
    @SerializedName("cover-large") val coverLarge: ImageDto?,
    val icon: ImageDto?,
    val background: ImageDto?,
    val forground: ImageDto?,
    @SerializedName("trophy-1st") val trophy1: ImageDto?,
    @SerializedName("trophy-2nd") val trophy2: ImageDto?,
    @SerializedName("trophy-3rd") val trophy3: ImageDto?,
    @SerializedName("trophy-4th") val trophy4: ImageDto?
)