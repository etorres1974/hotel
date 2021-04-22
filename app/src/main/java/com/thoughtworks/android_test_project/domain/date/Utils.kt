package com.thoughtworks.android_test_project

import java.time.LocalDate

operator fun LocalDate.rangeTo(other : LocalDate) = DateProgression(this, other)

