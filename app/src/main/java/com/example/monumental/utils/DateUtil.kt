package com.example.monumental.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateUtil {
    companion object {
        fun getDaysList(fromDate: LocalDate, toDate: LocalDate): List<LocalDate> {
            val daysList = mutableListOf<LocalDate>()
            var tempDate = fromDate
            while (tempDate.isBefore(toDate)) {
                daysList.add(tempDate)
                tempDate = tempDate.plusDays(1)
            }
            return daysList
        }
    }
}

fun LocalDate.getFormattedDateOfWeek(): String {
    val formatter = DateTimeFormatter.ofPattern("EEE")
    return this.format(formatter).uppercase()
}
