package com.example.data

import com.example.network.Session
import com.example.network.SpeedrunService
import com.example.network.model.dto.LatestRunDto
import com.example.network.model.dto.UserDto
import com.example.network.model.dto.UserRunDto
import com.example.storage.DatabaseSpeedrun
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Datamanager @Inject constructor(
    private val database: DatabaseSpeedrun,
    private val service: SpeedrunService,
    private val session: Session
) {

//    suspend fun getResources(): Map<String, String> {
//        val resourcesFromServer = service.getResources().data
//
//        val resourcesForDatabase = resourcesFromServer.map {
//            ResourceDataMapper.from(it)
//        }
//
//        database.resourcesDao().update(resourcesForDatabase)
//
//        return database.resourcesDao().getResourcesAsMap()
//    }

    suspend fun getLatestRuns(): List<LatestRunDto> {
        return service.getLatestRun().data
    }

    suspend fun getUserDetails(userId: String): UserDto {
        return service.getUserById(userId).data
    }

    suspend fun getUserRuns(userId: String): List<UserRunDto> {
        return service.getUserRuns(userId).data
    }
}