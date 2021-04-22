package com.thoughtworks.android_test_project

import java.time.DayOfWeek
import java.time.LocalDate

class DefaultHotel(
    override val name: String,
    override val classification: Int,
    override val weekDay: Price,
    override val weekendDay: Price
) : Hotel {

    override fun calculatePrice(startDay: LocalDate, endDay: LocalDate): Price {
        val weekDays = countDays(WEEK_DAYS,startDay, endDay)
        val weekendDays = countDays(WEEKEND_DAYS, startDay, endDay)
        val regular = (weekDays * weekDay.regular) + (weekendDays * weekendDay.regular)
        val reward = (weekDays * weekDay.reward) + (weekendDays * weekendDay.reward)
        return Price(regular, reward)
    }

    private fun countDays(dayList : List<DayOfWeek>, startDay: LocalDate, endDay: LocalDate): Int {
        var counter = 0
        for (day in startDay..endDay) {
            if (dayList.contains(day.dayOfWeek))
                counter++
        }
        return counter
    }

    companion object {
        private val WEEK_DAYS = listOf(
            DayOfWeek.FRIDAY,
            DayOfWeek.MONDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY
        )
        private val WEEKEND_DAYS = listOf(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY)
    }

}
