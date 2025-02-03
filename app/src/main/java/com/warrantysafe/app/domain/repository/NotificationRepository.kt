package com.warrantysafe.app.domain.repository

import com.warrantysafe.app.domain.model.Notification
import com.warrantysafe.app.domain.utils.Results

interface NotificationRepository {
    suspend fun addNotification(notification: String)
    suspend fun getNotifications(): Results<List<Notification>>
    suspend fun markNotificationAsRead(notificationId: Int)
}