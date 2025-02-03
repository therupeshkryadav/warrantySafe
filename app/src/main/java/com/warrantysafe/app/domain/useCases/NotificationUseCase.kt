package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.Notification
import com.warrantysafe.app.domain.repository.NotificationRepository
import com.warrantysafe.app.domain.utils.Results


// Use case for adding a notification
class AddNotificationUseCase(
    private val notificationRepository: NotificationRepository
) {
    // Execute method to add the notification
    suspend fun execute(notification: String) {
        notificationRepository.addNotification(notification)
    }
}

class GetNotificationsUseCase(private val notificationRepository: NotificationRepository) {

    suspend operator fun invoke(): Results<List<Notification>> {
        return notificationRepository.getNotifications()
    }
}

class MarkNotificationReadUseCase(
    private val notificationRepository: NotificationRepository
) {
    suspend operator fun invoke(notificationId: Int) {
        notificationRepository.markNotificationAsRead(notificationId)
    }
}


