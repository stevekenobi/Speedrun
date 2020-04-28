package com.example.network

import com.example.network.model.messages.LatestRunResponse
import com.example.network.model.messages.UserResponse
import com.example.network.model.messages.UserRunsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpeedrunService {

//    @GET("/api/resources")
//    suspend fun getResources(): ResourcesResponse

    @GET("runs?orderby=verify-date&direction=desc&embed=game,category,players")
    suspend fun getLatestRun(): LatestRunResponse

    @GET("users/{userId}")
    suspend fun getUserById(@Path("userId")userId: String): UserResponse

    @GET("runs?embed=game,category,players")
    suspend fun getUserRuns(@Query("user")userId: String): UserRunsResponse
}