package com.example.network.model.splits

import com.google.gson.annotations.SerializedName

data class SplitsDto(
    val duration: Double,
    @SerializedName("finish_time") val finishTime: Double,
    val gold: Boolean,
    val history: List<Double>,
    val name: String,
    val reduced: Boolean,
    val skipped: Boolean
)