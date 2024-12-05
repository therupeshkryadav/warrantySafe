package com.warrantysafe.app.presentation.notifification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.presentation.notifification.components.notificationList.NotificationList

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
        val notificationsList = emptyList<Notifications>()
//        val notificationsList = listOf(
//            Notifications(
//                isRead = true,
//                notification = "I have read the notification -->"
//            ),
//            Notifications(
//                isRead = false,
//                notification = "The notification is unread!! -->"
//            ),
//            Notifications(
//                isRead = true,
//                notification = "I have read the notification -->"
//            ),
//            Notifications(
//                isRead = false,
//                notification = "The Notification is unread!! -->"
//            ),
//            Notifications(
//                isRead = true,
//                notification = "I have read the notification -->"
//            )
//        )
        NotificationList(notifications = notificationsList)
    }
}

@Preview
@Composable
fun PreviewNotify(){
    NotificationScreen(navController = rememberNavController())
}