package com.example.monumental.domain.model

import com.example.monumental.constants.enums.Frequency
import java.time.DayOfWeek

sealed class FrequencyData(
    val frequency: Frequency = Frequency.DAILY,
) {
    companion object {
        fun fromObject(frequencyObject: Any?): FrequencyData? {
            if (frequencyObject == null) return null
            if (frequencyObject is Map<*, *>) {
                frequencyObject["frequency"]?.let {
                    return when (Frequency.valueOf(it as String)) {
                        Frequency.DAILY -> DailyFrequencyData()
                        Frequency.WEEKLY -> WeeklyFrequencyData(
                            day = frequencyObject["day"] as DayOfWeek?
                        )
                        Frequency.MONTHLY -> MonthlyFrequencyData(
                            dayOfMonth = frequencyObject["dayOfMonth"] as Int?
                        )
                        Frequency.YEARLY -> YearlyFrequencyData(
                            month = frequencyObject["month"] as Int?,
                            dayOfMonth = frequencyObject["dayOfMonth"] as Int?
                        )
                        Frequency.CUSTOM -> CustomFrequencyData(
                            days = frequencyObject["days"] as List<DayOfWeek>?,
                            dayOfMonth = frequencyObject["dayOfMonth"] as Int?,
                            month = frequencyObject["month"] as Int?
                        )
                    }
                }
            }
            return null
        }
    }
}

class DailyFrequencyData : FrequencyData(frequency = Frequency.DAILY)

data class WeeklyFrequencyData(
    val day: DayOfWeek?,
) : FrequencyData(frequency = Frequency.WEEKLY)

data class MonthlyFrequencyData(
    val dayOfMonth: Int?,
) : FrequencyData(frequency = Frequency.MONTHLY)

data class YearlyFrequencyData(
    val month: Int?,
    val dayOfMonth: Int?,
) : FrequencyData(frequency = Frequency.YEARLY)

data class CustomFrequencyData(
    val days: List<DayOfWeek>?,
) : FrequencyData(frequency = Frequency.CUSTOM)