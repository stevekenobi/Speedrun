package com.example.speedrun.utils

import org.junit.Assert.assertEquals
import org.junit.Test


class PlaceTextTests {

    @Test
    fun zeroPlace() {
        val place = 0
        assertEquals(" - ", RunsTextUtils.setPlaceForLeaderboard(place))
    }

    @Test
    fun nullPlace() {
        val place: Int? = 0
        assertEquals(" - ", RunsTextUtils.setPlaceForLeaderboard(place))
    }

    @Test
    fun firstPlace() {
        val place = 1
        assertEquals("1st", RunsTextUtils.setPlaceForLeaderboard(place))
    }

    @Test
    fun secondPlace() {
        val place = 2
        assertEquals("2nd", RunsTextUtils.setPlaceForLeaderboard(place))
    }

    @Test
    fun thirdPlace() {
        val place = 3
        assertEquals("3rd", RunsTextUtils.setPlaceForLeaderboard(place))
    }

    @Test
    fun ninthPlace() {
        val place = 9
        assertEquals("9th", RunsTextUtils.setPlaceForLeaderboard(place))
    }

    @Test
    fun eleventhPlace() {
        val place = 11
        assertEquals("11th", RunsTextUtils.setPlaceForLeaderboard(place))
    }

    @Test
    fun twelfthPlace() {
        val place = 12
        assertEquals("12th", RunsTextUtils.setPlaceForLeaderboard(place))
    }

    @Test
    fun twentyFirstPlace() {
        val place = 21
        assertEquals("21st", RunsTextUtils.setPlaceForLeaderboard(place))
    }

    @Test
    fun thirtySecondPlace() {
        val place = 32
        assertEquals("32nd", RunsTextUtils.setPlaceForLeaderboard(place))
    }

    @Test
    fun hundredStuffPlaceThird() {
        val place = 523
        assertEquals("523rd", RunsTextUtils.setPlaceForLeaderboard(place))
    }

    @Test
    fun hundredStuffPlaceSecond() {
        val place = 732
        assertEquals("732nd", RunsTextUtils.setPlaceForLeaderboard(place))
    }

    @Test
    fun hundredRandomPlace() {
        val place = 256
        assertEquals("256th", RunsTextUtils.setPlaceForLeaderboard(place))
    }

    @Test
    fun hundredEleventhPlace() {
        val place = 611
        assertEquals("611th", RunsTextUtils.setPlaceForLeaderboard(place))
    }

    @Test
    fun hundredTwelfthPlace() {
        val place = 912
        assertEquals("912th", RunsTextUtils.setPlaceForLeaderboard(place))
    }
}