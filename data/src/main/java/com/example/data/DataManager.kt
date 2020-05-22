package com.example.data

import com.example.data.speedrun.SpeedrunDataManager
import com.example.data.splits.SplitsDataManager

interface DataManager {
    val splitsDataManager: SplitsDataManager

    val speedrunDataManager: SpeedrunDataManager
}