package com.warrantysafe.app.data.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.firestore.FirebaseFirestore
import com.warrantysafe.app.utils.NotificationHelper

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        remoteMessage.notification?.let {
            val title = it.title ?: "Upcoming Feature"
            val message = it.body ?: "New update coming soon!"

            Log.d("FCM", "Received Notification: $title - $message")

            // Save notification to Firestore
            saveNotificationToFirestore(message)

            // Send notification using NotificationHelper
            NotificationHelper.sendNotification(this, title, message)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "New Token: $token")

        // You can store this token in Firestore or your backend if needed
    }

    private fun saveNotificationToFirestore(message: String) {
        val db = FirebaseFirestore.getInstance()
        val notification = hashMapOf("upcomingNotification" to message)

        db.collection("upcoming_notifications")
            .add(notification)
            .addOnSuccessListener {
                Log.d("FCM", "Notification saved to Firestore")
            }
            .addOnFailureListener {
                Log.e("FCM", "Failed to save notification: ${it.message}")
            }
    }
}
