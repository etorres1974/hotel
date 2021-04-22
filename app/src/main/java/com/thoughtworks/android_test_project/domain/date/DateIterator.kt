package com.thoughtworks.android_test_project

import java.time.LocalDate

class DateIterator(
    val start : LocalDate,
    val endInclusive: LocalDate
) : Iterator<LocalDate> {
    private var current = start

    override fun hasNext(): Boolean = current <= endInclusive

    override fun next(): LocalDate {
        val next = current
        current  = current.plusDays(1)
        return next
    }

}


