package com.example.speedrun.utils

import java.util.concurrent.TimeUnit

object RunTimeConverter {
    fun from(time: Long, showMills: Boolean): String {
        val hours = TimeUnit.MILLISECONDS.toHours(time)
        val mins = TimeUnit.MILLISECONDS.toMinutes(time) - (hours * 60)

        val secs = TimeUnit.MILLISECONDS.toSeconds(time) -( mins * 60) - (hours * 3600)

        val mills = time.rem(1000)

        if (showMills)
            return "${hours}h ${mins}m ${secs}s ${mills}ms"

        return "${hours}h ${mins}m ${secs}s"
    }

    fun from(time: Double, showMills: Boolean) : String {
        val mills = ((time - time.toInt()) * 1000).toInt()

        val hours = (time.toInt() / 3600)

        val mins = (time.toInt() / 60 - hours * 60)

        val secs = time.toInt() - (mins * 60) - (hours * 3600)

        when {
            hours == 0 && mins == 0 -> {
                if (showMills)
                    return "${secs}s ${mills}ms"

                return "${secs}s"
            }
            hours == 0 -> {
                if (showMills)
                    return "${mins}m ${secs}s ${mills}ms"

                return "${mins}m ${secs}s"
            }
        }
        if (showMills)
            return "${hours}h ${mins}m ${secs}s ${mills}ms"

        return "${hours}h ${mins}m ${secs}s"
    }
}