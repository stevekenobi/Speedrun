package com.example.data

import com.example.data.speedrun.SpeedrunDataManager
import com.example.data.splits.SplitsDataManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommonDataManager @Inject constructor(
    override val splitsDataManager: SplitsDataManager,
    override val speedrunDataManager: SpeedrunDataManager
) : DataManager