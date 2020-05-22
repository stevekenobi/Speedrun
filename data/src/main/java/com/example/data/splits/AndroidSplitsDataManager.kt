package com.example.data.splits

import com.example.network.api.SplitsService
import com.example.network.model.splits.SplitsDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidSplitsDataManager @Inject constructor(override val serviceSplits: SplitsService) :
    SplitsDataManager {

    override suspend fun getSplitsForRun(runId: String): List<SplitsDto> {
        return serviceSplits.getSplitsForRun(runId).run.splits
    }
}