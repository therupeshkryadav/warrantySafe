package com.warrantysafe.app.presentation.notifification.components.notificationList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.notifification.Notifications
import com.warrantysafe.app.presentation.notifification.components.NotificationItem

@Composable
fun NotificationList(
    notifications: List<Notifications> // Changed to a flat list of products
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(color = colorResource(R.color.white))
    ) {
        items(notifications) { notification ->
            NotificationItem(
                isRead = notification.isRead,
                notification = notification.notification
            )
        }
    }
}

@Preview
@Composable
private fun PreviewNotificationsList() {
    val notificationsList = listOf(
        Notifications(
            isRead = true,
            notification = "I have read the notification -->"
        ),
        Notifications(
            isRead = false,
            notification = "The notification is unread!! -->"
        ),
        Notifications(
            isRead = true,
            notification = "I have read the notification -->"
        ),
        Notifications(
            isRead = false,
            notification = "The Notification is unread!! -->"
        ),
        Notifications(
            isRead = true,
            notification = "I have read the notification -->"
        )
    )
    NotificationList(notifications = notificationsList)

}