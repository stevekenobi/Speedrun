package com.example.network

import com.example.network.model.messages.ResourcesResponse
import retrofit2.http.GET

interface SpeedrunService {

    @GET
    suspend fun getResources(): ResourcesResponse
}