package com.warrantysafe.app.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.toObject
import com.warrantysafe.app.domain.model.Notification
import com.warrantysafe.app.domain.repository.NotificationRepository
import com.warrantysafe.app.domain.utils.Results
import kotlinx.coroutines.tasks.await

class NotificationRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : NotificationRepository {

    private val usersCollection = firestore.collection("users")

    override suspend fun addNotification(notification: String) {
        Log.d("Noted","Add 1")
        val userId = firebaseAuth.currentUser?.uid ?: return
        val notificationRef = usersCollection.document(userId).collection("notifications")
        Log.d("Noted","Add 2")
        val newNotification = Notification(
            id = System.currentTimeMillis().toInt(), // Generate a unique ID
            notification = notification,
            isRead = false
        )
        Log.d("Noted","Add 3")

        try {
            Log.d("Noted","Add 4")
            notificationRef.document(newNotification.id.toString()).set(newNotification).await()
            Log.d("Noted","Add 5")
        } catch (e: Exception) {
            Log.d("Noted","Add 6")
            e.printStackTrace()
        }
    }

    override suspend fun getNotifications(): Results<List<Notification>> {
        val userId = firebaseAuth.currentUser?.uid ?: return Results.Failure(Exception("User not logged in"))

        return try {
            val snapshot = usersCollection.document(userId)
                .collection("notifications").get().await()

            if (!snapshot.isEmpty) {
                val notifications = snapshot.documents.mapNotNull { document ->
                    try {
                        Notification(
                            id = document.id.toIntOrNull() ?: 0, // Convert id safely
                            notification = document.getString("notification") ?: "No message",
                            isRead = document.getBoolean("isRead") ?: false
                        )
                    } catch (e: Exception) {
                        Log.e("NotificationMapping", "Error mapping document: ${e.localizedMessage}")
                        null // Skip invalid documents
                    }
                }
                Results.Success(notifications)
            } else {
                Log.d("Get Notify","notifications in else: Empty")
                Results.Success(emptyList())
            }
        } catch (e: Exception) {
            Results.Failure(Exception(e.localizedMessage ?: "Failed to fetch notifications"))
        }
    }


    override suspend fun markNotificationAsRead(notificationId: Int) {
        val userId = firebaseAuth.currentUser?.uid ?: return
        val notificationRef = usersCollection.document(userId)
            .collection("notifications").document(notificationId.toString())

        try {
            notificationRef.update("isRead", true).await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
