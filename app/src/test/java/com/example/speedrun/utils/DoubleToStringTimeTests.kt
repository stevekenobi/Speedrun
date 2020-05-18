package com.example.speedrun.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class DoubleToStringTimeTests {

    @Test
    fun justMillsTest() {
        val time = 0.543
        assertEquals("0s 543ms", RunTimeConverter.from(time, true))
    }

    @Test
    fun secsTest() {
        val time = 4.235
        assertEquals("4s 235ms", RunTimeConverter.from(time, true))
    }

    @Test
    fun minsTest() {
        val time = 157.12
        assertEquals("2m 37s 120ms", RunTimeConverter.from(time, true))
    }

    @Test
    fun minsTestNoMills() {
        val time = 157.12
        assertEquals("2m 37s", RunTimeConverter.from(time, false))
    }

    @Test
    fun severalMinsTest() {
        val time = 2702.0
        assertEquals("45m 2s 0ms", RunTimeConverter.from(time, true))
    }

    @Test
    fun severalMinsTestNoMills() {
        val time = 2702.0
        assertEquals("45m 2s", RunTimeConverter.from(time, false))
    }

    @Test
    fun hoursTest() {
        val time = 4841.019
        assertEquals("1h 20m 41s 19ms", RunTimeConverter.from(time, true))
    }

    @Test
    fun hoursTestNoMills() {
        val time = 4841.019
        assertEquals("1h 20m 41s", RunTimeConverter.from(time, false))
    }

    @Test
    fun severalHours() {
        val time = 54115.0
        assertEquals("15h 1m 55s 0ms", RunTimeConverter.from(time, true))
    }

    @Test
    fun severalHoursNoMills() {
        val time = 54115.0
        assertEquals("15h 1m 55s", RunTimeConverter.from(time, false))
    }
}