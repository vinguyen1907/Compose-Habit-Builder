package com.example.monumental.data.repository.local

import android.util.Log
import com.example.monumental.domain.model.Habit
import com.example.monumental.domain.repository.HabitRepository
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class HabitRepositoryImpl(
    private val firestore: FirebaseFirestore
) : HabitRepository {
    override fun getHabits(): Flow<List<DocumentChange>?> = callbackFlow {
        val listener = firestore
            .collection("habits")
            .orderBy("createdAt", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                } else {
                    trySend(snapshot?.documentChanges).isSuccess
                }
            }
        awaitClose {
            listener.remove()
        }
    }

    override suspend fun addHabit(habit: Habit, onResult: (Throwable?) -> Unit) {
        // if authenticated => add to remote db

        firestore.collection("habits").add(habit)
            .addOnCompleteListener() {
                onResult(it.exception)
            }

    }
}