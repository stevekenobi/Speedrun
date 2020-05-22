package com.example.data.speedrun

import com.example.network.apis.SpeedrunService
import com.example.network.model.dto.*
import com.example.network.utils.NetworkConstants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidSpeedrunDataManager @Inject constructor(override val serviceSpeedrun: SpeedrunService) :
    SpeedrunDataManager {

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

    override  suspend fun getLatestRuns(): List<LatestRunDto> {
        return serviceSpeedrun.getLatestRun().data
    }

    override  suspend fun getRecentGames(offset: Int): List<GameDto> {
        return serviceSpeedrun.getGames(offset).data
    }

    override   suspend fun getSeries(): List<SeriesDto> {
        return serviceSpeedrun.getSeries().data
    }

    override suspend fun getUserDetails(userId: String): UserDto {
        return serviceSpeedrun.getUserById(userId).data
    }

    override  suspend fun getUserRuns(userId: String): List<UserRunDto> {
        val userRuns = serviceSpeedrun.getUserRuns(userId).data

        val userPbs = serviceSpeedrun.getPBsForUser(userId).data

        userRuns.map { run ->
            run.place = userPbs.find { pb: UserPBDto -> pb.run.id == run.id }?.place
        }

        return userRuns
    }

    override   suspend fun getGamesModeratedBy(moderatorId: String): List<GameDto> {
        return serviceSpeedrun.getGamesModeratedBy(moderatorId).data
    }

    override suspend fun getSeriesModeratedBy(moderatorId: String): List<SeriesDto> {
        return serviceSpeedrun.getSeriesModeratedBy(moderatorId).data
    }

    override  suspend fun getGameDetails(gameId: String): GameDetailsDto {
        return serviceSpeedrun.getGameDetails(gameId).data
    }

    override   suspend fun getCategories(gameId: String): List<CategoryDto> {
        return serviceSpeedrun.getGameCategories(gameId).data
    }

    override   suspend fun getLevelCategories(levelId: String): List<CategoryDto> {
        return serviceSpeedrun.getLevelCategories(levelId).data
    }

    /**
     *  TODO Improve users logic
     */
    override   suspend fun getCategoryLeaderboard(gameId: String, categoryId: String): List<LeaderboardRunDto> {
        val response = serviceSpeedrun.getLeaderboardForCategory(gameId, categoryId).data

        val allPlayers = response.players.data.distinct()

        response.runs.forEach { run ->
            val usersToAdd = mutableListOf<UserDto>()

            run.run.players.forEach { player ->
                if (player.rel == NetworkConstants.REL_GUEST) {
                    val nextUser = allPlayers.filter {user ->
                        user.rel == NetworkConstants.REL_GUEST && user.name == player.name
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

    override   suspend fun getLevelLeaderboard(gameId: String,levelId: String, categoryId: String): List<LeaderboardRunDto> {
        val response = serviceSpeedrun.getCategoryLeaderboardForLevel(gameId, levelId, categoryId).data

        val allPlayers = response.players.data.distinct()

        response.runs.forEach { run ->
            val usersToAdd = mutableListOf<UserDto>()

            run.run.players.forEach { player ->
                if (player.rel == NetworkConstants.REL_GUEST) {
                    val nextUser = allPlayers.filter {user ->
                        user.rel == NetworkConstants.REL_GUEST && user.name == player.name
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

    override  suspend fun getRunDetails(runId: String): RunDto {
        return serviceSpeedrun.getRunDetails(runId).data
    }

}