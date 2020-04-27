package com.example.speedrun.utils

import java.util.concurrent.TimeUnit

object RunTimeConverter {
    fun from(time: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(time)
        val mins = TimeUnit.MILLISECONDS.toMinutes(time) - (hours * 60)

        val secs = TimeUnit.MILLISECONDS.toSeconds(time) -( mins * 60) - (hours * 3600)

        val mills = time.rem(1000)

        return "${hours}h ${mins}m ${secs}s ${mills}ms"
    }

    fun from(time: Double) : String {
        return time.toString() // TODO Convert properly
    }
}