package com.warrantysafe.app.presentation.ui.notifification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.presentation.ui.notifification.components.notificationList.NotificationList

data class Notifications(
    val isRead: Boolean,
    val notification: String
)

@Composable
fun NotificationScreen(navController: NavController) {
// Content under the TopAppBar
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
}

@Preview(showBackground = true)
@Composable
fun PreviewNotify(){
    NotificationScreen(navController = rememberNavController())
}