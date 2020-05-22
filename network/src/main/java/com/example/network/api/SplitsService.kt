package com.example.network.api

import com.example.network.model.splits.SplitResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SplitsService {

    @GET("runs/{runId}")
    suspend fun getSplitsForRun(@Path("runId") runId: String): SplitResponse
}