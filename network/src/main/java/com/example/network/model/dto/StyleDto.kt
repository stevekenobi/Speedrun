package com.example.network.model.dto

import com.google.gson.annotations.SerializedName

data class StyleDto(
    val style: String,
    @SerializedName("color-from") val colorFrom: ColorDto?,
    @SerializedName("color-to") val colorTo: ColorDto?,
    val color: ColorDto?
)