package com.example.network.apis

import com.example.network.model.messages.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpeedrunService {

//    @GET("/api/resources")
//    suspend fun getResources(): ResourcesResponse

    /**
     *  Main Activity
     */
    @GET("runs?orderby=verify-date&max=50&direction=desc&embed=game,category,players")
    suspend fun getLatestRun(): LatestRunResponse

    @GET("games?orderby=released&direction=desc&max=50")
    suspend fun getGames(@Query("offset") offset: Int): GamesResponse

    @GET("series?max=50&orderby=created&direction=desc")
    suspend fun getSeries(): SeriesResponse

    /**
     *  User Details Activity
     */
    @GET("users/{userId}")
    suspend fun getUserById(@Path("userId") userId: String): UserResponse

    @GET("users/{userId}/personal-bests?embed=games,categories,players")
    suspend fun getPBsForUser(@Path("userId") userId: String): PBResponse

    @GET("runs?embed=game,category,players&orderby=date&direction=desc")
    suspend fun getUserRuns(@Query("user") userId: String): UserRunsResponse

    @GET("games")
    suspend fun getGamesModeratedBy(@Query("moderator") modId: String): GamesResponse

    @GET("series")
    suspend fun getSeriesModeratedBy(@Query("moderator") modId: String): SeriesResponse

    /**
     *  Game Details Activity
     */
    @GET("games/{gameId}?embed=categories,levels,developers,moderators,platforms")
    suspend fun getGameDetails(@Path("gameId") gameId: String): GameDetailsResponse

    @GET("games/{gameId}/categories")
    suspend fun getGameCategories(@Path("gameId") gameId: String): CategoriesResponse

    @GET("levels/{levelId}/categories")
    suspend fun getLevelCategories(@Path("levelId") levelId: String): CategoriesResponse

    @GET("leaderboards/{gameId}/category/{categoryId}?embed=players")
    suspend fun getLeaderboardForCategory(
        @Path("gameId") gameId: String,
        @Path("categoryId") categoryId: String
    ): LeaderboardResponse

    @GET("leaderboards/{gameId}/level/{levelId}/{categoryId}?embed=players")
    suspend fun getCategoryLeaderboardForLevel(
        @Path("gameId") gameId: String,
        @Path("levelId") levelId: String,
        @Path("categoryId") categoryId: String
    ): LeaderboardResponse

    /**
     *  Run Details Activity
     */
    @GET("runs/{runId}?embed=category,players")
    suspend fun getRunDetails(@Path("runId") runId: String): RunResponse
}