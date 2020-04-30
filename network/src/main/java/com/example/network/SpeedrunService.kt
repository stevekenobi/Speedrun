package com.example.network

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
    @GET("runs?orderby=verify-date&direction=desc&embed=game,category,players")
    suspend fun getLatestRun(): LatestRunResponse

    /**
     *  User Details Activity
     */
    @GET("users/{userId}")
    suspend fun getUserById(@Path("userId") userId: String): UserResponse

    @GET("users/{userId}/personal-bests?embed=games,categories,players")
    suspend fun getPBsForUser(@Path("userId") userId: String): PBResponse

    @GET("runs?embed=game,category,players&orderby=date&direction=desc")
    suspend fun getUserRuns(@Query("user") userId: String): UserRunsResponse

    /**
     *  Game Details Activity
     */
    @GET("games/{gameId}?embed=categories,developers,moderators,platforms")
    suspend fun getGameDetails(@Path("gameId") gameId: String): GameDetailsResponse
}