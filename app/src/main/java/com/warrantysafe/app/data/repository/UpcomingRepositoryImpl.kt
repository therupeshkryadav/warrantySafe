package com.warrantysafe.app.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.warrantysafe.app.domain.model.Upcoming
import com.warrantysafe.app.domain.repository.UpcomingRepository
import kotlinx.coroutines.tasks.await

class UpcomingRepositoryImpl : UpcomingRepository {

    private val firestore = FirebaseFirestore.getInstance()

    override suspend fun getUpcomingNotifications(): List<Upcoming> {
        return try {
            val snapshot = firestore.collection("upcoming_notifications")
                .orderBy("timestamp") // Ensure Firestore has a "timestamp" field for ordering
                .get()
                .await()

            snapshot.documents.map { document ->
                Upcoming(
                    upcomingNotification = document.getString("upcomingNotification") ?: "No upcoming notification"
                )
            }
        } catch (e: Exception) {
            Log.e("Firestore", "Error fetching upcoming notifications: ${e.message}")
            emptyList()
        }
    }
}
