package com.example.monumental.domain.model

import com.example.monumental.constants.enums.Frequency
import com.example.monumental.constants.enums.HabitColor
import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp
import java.time.LocalTime
import java.util.UUID


data class Habit(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
//    val description: String,
//    val icon: String,
    val color: HabitColor,
    var frequencyData: FrequencyData?,
    val notification: Boolean,
    @get: PropertyName("reminderString")
    val reminder: LocalTime,
    val goal: Int,
//    val progress: Int,
//    val streak: Int,
    val completed: Boolean,
    val createdAt: Timestamp,
    @ServerTimestamp val updatedAt: Timestamp
) {
    constructor() : this(
        title = "",
        color = HabitColor.ORANGE,
        frequencyData = null,
        notification = false,
        reminder = LocalTime.now(),
        goal = 0,
        completed = false,
        createdAt = Timestamp.now(),
        updatedAt = Timestamp.now()
    )
}
