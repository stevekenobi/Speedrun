package com.example.speedrun.utils

import com.example.network.model.dto.UserDto
import com.example.network.model.dto.UserRunDto
import com.example.network.utils.enums.UserEnums

object RunsTextUtils {

    fun setUserGameRun(run: UserRunDto): String {

        val result = run.category.data.name +
                " " + RunTimeConverter.from(run.times.primary_t)

        return result + setPlaceForRecent(run.place)
    }

    fun setPlaceForLeaderboard(place: Int?): String {
        if (place == null)
            return " - "

        val placeSuff = when {
            place.rem(100) == 11 -> "th"
            place.rem(100) == 12 -> "th"
            place.rem(10) == 1 -> "st"
            place.rem(10) == 2 -> "nd"
            place.rem(10) == 3 -> "rd"

            else -> "th"
        }

        return "$place$placeSuff"
    }

    fun setPlaceForRecent(place: Int?): String {
        if (place == null)
            return " - "

        val placeSuff = when {
            place.rem(100) == 11 -> "th"
            place.rem(100) == 12 -> "th"
            place.rem(10) == 1 -> "st"
            place.rem(10) == 2 -> "nd"
            place.rem(10) == 3 -> "rd"

            else -> "th"
        }

        return " ($place$placeSuff place)"
    }

    fun setPlayerText(player: UserDto): String? {
        if (player.rel == UserEnums.REL_GUEST) {
            return player.name
        }

        return player.names?.international
    }

    fun setPlayersText(players: List<UserDto>): String? {
        if (players.size == 1)
            return setPlayerText(players[0])

        if (players.size == 2)
            return """${setPlayerText(players[0])} and ${setPlayerText(players[1])}"""

        var result = ""
        players.forEach {
            result += setPlayerText(it) + ", "
        }
        return result
    }
}