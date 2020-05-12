package com.example.data

import com.example.network.Session
import com.example.network.SpeedrunService
import com.example.network.model.dto.*
import com.example.network.utils.UserEnums
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
        val userRuns = service.getUserRuns(userId).data

        val userPbs = service.getPBsForUser(userId).data

        userRuns.map {
            it.place = userPbs.find { pb: UserPBDto -> pb.run.id == it.id }?.place
        }

        return userRuns
    }

    suspend fun getGameDetails(gameId: String): GameDetailsDto {
        return service.getGameDetails(gameId).data
    }

    suspend fun getCategories(gameId: String): List<CategoryDto> {
        return service.getGameCategories(gameId).data
    }

    suspend fun getLevels(gameId: String): List<LevelDto> {
        return service.getGameLevels(gameId).data
    }

    /**
     *  TODO Improve users logic
     */
    suspend fun getCategoryLeaderboard(gameId: String, categoryId: String): List<LeaderboardRunDto> {
        val response = service.getLeaderboardForCategory(gameId, categoryId).data

        val allPlayers = response.players.data.distinct()

        response.runs.forEach { run ->
            val usersToAdd = mutableListOf<UserDto>()

            run.run.players.forEach { player ->
                if (player.rel == UserEnums.REL_GUEST) {
                    val nextUser = allPlayers.filter {user ->
                        user.rel == UserEnums.REL_GUEST && user.name == player.name
                    }

                    usersToAdd.addAll(nextUser)
                } else {
                    val nextUser = allPlayers.filter { user ->
                        user.id == player.id
                    }

                    usersToAdd.addAll(nextUser)
                }
            }

            run.run.playersToDisplay = usersToAdd
        }

        return response.runs
    }

    suspend fun getLevelLeaderboard(gameId: String,levelId: String, categoryId: String): List<LeaderboardRunDto> {
        val response = service.getCategoryLeaderboardForLevel(gameId, levelId, categoryId).data

        val allPlayers = response.players.data.distinct()

        response.runs.forEach { run ->
            val usersToAdd = mutableListOf<UserDto>()

            run.run.players.forEach { player ->
                if (player.rel == UserEnums.REL_GUEST) {
                    val nextUser = allPlayers.filter {user ->
                        user.rel == UserEnums.REL_GUEST && user.name == player.name
                    }

                    usersToAdd.addAll(nextUser)
                } else {
                    val nextUser = allPlayers.filter { user ->
                        user.id == player.id
                    }

                    usersToAdd.addAll(nextUser)
                }
            }

            run.run.playersToDisplay = usersToAdd
        }

        return response.runs
    }

    suspend fun getRunDetails(runId: String): RunDto {
        return service.getRunDetails(runId).data
    }
}