package com.example.network.model.dto

import android.os.Parcelable
import com.example.network.model.Link
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RunForLeaderboardDto(
    val id: String,
    var place: Int?,
    val weblink: String,
    val game: String,
    val level: String?,
    val category: String,
    val videos: VideosDto,
    val comment: String,
    val status: StatusDto,
    val players: List<PlayerDto>,
    val date: String,
    val submitted: String,
    val times: TimeDto,
    val splits: Link,
    val system: SystemDto,
    val links: List<Link>
) : Parcelable
