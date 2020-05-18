package com.example.network.model.dto

import com.google.gson.annotations.SerializedName

data class RulesetDto(
    @SerializedName("show-milliseconds") val showMills: Boolean,
    @SerializedName("require-verification") val reqVerification: Boolean,
    @SerializedName("require-video") val reqVideo: Boolean,
    @SerializedName("run-times") val runTimes: List<String>,
    @SerializedName("default-time") val defaultTime: String,
    @SerializedName("emulators-allowed") val emuAllowed: Boolean
)