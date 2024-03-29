package com.example.data.splits

import com.example.network.api.SplitsService
import com.example.network.model.splits.SplitsDto

interface SplitsDataManager {
    val serviceSplits: SplitsService

    suspend fun getSplitsForRun(runId: String): List<SplitsDto>
}