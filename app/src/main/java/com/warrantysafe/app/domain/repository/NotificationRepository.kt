package com.warrantysafe.app.domain.repository

import com.warrantysafe.app.domain.model.Notification

interface NotificationRepository {
    suspend fun getNotifications(): List<Notification>
}