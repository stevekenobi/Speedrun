package com.example.network.model.splits

import com.google.gson.annotations.SerializedName

data class SplitRunDto(
    val attempts: Int,
    val id: Long,
    @SerializedName("image_url") val imageUrl: String?,
    val name: String,
    val path: String,
    val program: String?,
    val splits: List<SplitsDto>,
    @SerializedName("sum_of_best") val sumOfBest: Double,
    val time: Double,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("video_url") val videoUrl: String?
)