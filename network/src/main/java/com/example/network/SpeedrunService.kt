package com.example.network

import com.example.network.model.messages.LatestRunResponse
import retrofit2.http.GET

interface SpeedrunService {

//    @GET("/api/resources")
//    suspend fun getResources(): ResourcesResponse

    @GET("runs?orderby=verify-date&direction=desc&embed=game,category,players")
    suspend fun getLatestRun(): LatestRunResponse
}