package com.warrantysafe.app.presentation.ui.screens.notifification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.warrantysafe.app.domain.model.Notification
import com.warrantysafe.app.presentation.ui.screens.notifification.components.NotificationItem

@Composable
fun NotificationScreen(
    notificationList: List<Notification>
) {
    // State to track the selected notification item by its unique ID
    val selectedNotificationId: MutableState<String?> = remember { mutableStateOf(null) }

    // Content under the TopAppBar
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(notificationList) { notification ->
                // Determine if the notification is selected
                val isSelected = selectedNotificationId.value == notification.notification

                NotificationItem(
                    notification = notification.notification
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNotify() {
    val notifications = listOf(
        Notification(notification = "This is my First Notification!!"),
        Notification(notification = "This is my Second Notification!!"),
        Notification(notification = "This is my Third Notification!!"),
        Notification(notification = "This is my Fourth Notification!!"),
        Notification(notification = "This is my Fifth Notification!!")
    )
    NotificationScreen(
        notificationList = notifications
    )
}
