package com.warrantysafe.app.presentation.ui.screens.notifification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.domain.model.Notification
import com.warrantysafe.app.presentation.ui.screens.notifification.components.notificationList.NotificationList

data class Notifications(
    val isRead: Boolean,
    val notification: String
)

@Composable
fun NotificationScreen(
    notificationList: List<Notification>,
    navController: NavController
) {
// Content under the TopAppBar
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        NotificationList(notifications = notificationList)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNotify(){
    NotificationScreen(
        navController = rememberNavController(),
        notificationList = listOf(
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
    )
}