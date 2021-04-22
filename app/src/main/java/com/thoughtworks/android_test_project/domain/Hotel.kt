package com.thoughtworks.android_test_project

import android.icu.util.Calendar
import java.time.LocalDate

interface Hotel {
    val name: String
    val classification: Int
    val weekDay: Price
    val weekendDay: Price
    fun calculatePrice(startDay : LocalDate, endDay : LocalDate) : Price
}