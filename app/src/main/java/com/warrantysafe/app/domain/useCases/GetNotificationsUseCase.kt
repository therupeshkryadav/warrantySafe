package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.Notification
import com.warrantysafe.app.domain.repository.NotificationRepository

class GetNotificationsUseCase(private val notificationRepository: NotificationRepository) {
    suspend operator fun invoke(): List<Notification> {
        return notificationRepository.getNotifications()
    }
}