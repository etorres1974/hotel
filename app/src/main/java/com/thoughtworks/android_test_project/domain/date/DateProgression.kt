package com.thoughtworks.android_test_project

import java.time.LocalDate

class DateProgression(
    override val start : LocalDate,
    override val endInclusive: LocalDate
) : Iterable<LocalDate>, ClosedRange<LocalDate> {

    override fun iterator(): Iterator<LocalDate> = DateIterator(start, endInclusive)
}


