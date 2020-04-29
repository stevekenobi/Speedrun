package com.example.speedrun.utils

import com.example.network.model.dto.UserRunDto

object RunsTextUtils {

    fun setUserGameRun(run: UserRunDto): String {

        val result = run.category.data.name +
                " " + RunTimeConverter.from(run.times.primary_t)

        if (run.place == null)
            return result

        val placeSuff = when {
            run.place?.rem(100) == 11 -> "th"
            run.place?.rem(100) == 12 -> "th"
            run.place?.rem(10) == 1 -> "st"
            run.place?.rem(10) == 2 -> "nd"
            run.place?.rem(10) == 3 -> "rd"

            else -> "th"
        }

        return result + " (" + run.place + placeSuff + " place)"
    }
}