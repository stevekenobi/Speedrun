package com.example.data.speedrun

import com.example.network.api.SpeedrunService
import com.example.network.model.dto.*

interface SpeedrunDataManager {
    val serviceSpeedrun: SpeedrunService

    suspend fun getLatestRuns(): List<LatestRunDto>
    suspend fun getRecentGames(offset: Int): List<GameDto>
    suspend fun getSeries(): List<SeriesDto>
    suspend fun getUserDetails(userId: String): UserDto
    suspend fun getUserRuns(userId: String): List<UserRunDto>
    suspend fun getGamesModeratedBy(moderatorId: String): List<GameDto>
    suspend fun getSeriesModeratedBy(moderatorId: String): List<SeriesDto>
    suspend fun getGameDetails(gameId: String): GameDetailsDto
    suspend fun getCategories(gameId: String): List<CategoryDto>
    suspend fun getLevelCategories(levelId: String): List<CategoryDto>
    suspend fun getCategoryLeaderboard(gameId: String, categoryId: String): List<LeaderboardRunDto>
    suspend fun getLevelLeaderboard(gameId: String, levelId: String, categoryId: String): List<LeaderboardRunDto>
    suspend fun getRunDetails(runId: String): RunDto

}