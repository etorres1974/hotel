package com.thoughtworks.android_test_project

import java.time.LocalDate

interface HotelComparator {
    fun findBestPrice(
        startDay: LocalDate,
        endDate: LocalDate,
        hotels: List<Hotel>,
        reward : Boolean
    ): Hotel
}