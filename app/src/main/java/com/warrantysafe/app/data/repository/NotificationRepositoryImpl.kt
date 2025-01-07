package com.warrantysafe.app.data.repository

import com.warrantysafe.app.domain.model.Notification
import com.warrantysafe.app.domain.repository.NotificationRepository

class NotificationRepositoryImpl: NotificationRepository {

    private val notificationList = mutableListOf<Notification>(
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my First Notification!!")
        )

  //  private val notificationList = mutableListOf<Notification>()

    override suspend fun getNotifications(): List<Notification> {
        return notificationList
    }
}