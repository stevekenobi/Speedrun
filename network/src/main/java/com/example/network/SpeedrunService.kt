package com.example.network

import com.example.network.model.messages.LatestRunResponse
import com.example.network.model.messages.ResourcesResponse
import retrofit2.http.GET

interface SpeedrunService {

//    @GET("/api/resources")
//    suspend fun getResources(): ResourcesResponse

    @GET("runs?orderby=date&direction=desc&embed=game,category,players")
    suspend fun getLatestRun(): LatestRunResponse
}