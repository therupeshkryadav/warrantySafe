package com.warrantysafe.app.presentation.ui.screens.notifification

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.Notification
import com.warrantysafe.app.presentation.ui.screens.notifification.components.NotificationItem

@Composable
fun NotificationScreen(
    notificationList: List<Notification>
) {
    // Content under the TopAppBar
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (notificationList.isEmpty()) {
            // If notifications are empty, display the "No Notifications Available" message
            Box(
                modifier = Modifier
                    .fillMaxSize(), // Make the Box take up the full available space
                contentAlignment = Alignment.Center // Center the content inside the Box
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.not_found_notification),
                        contentDescription = "No notifications available",
                        modifier = Modifier
                            .size(200.dp)
                            .padding(top = 16.dp) // Optional padding for spacing
                    )
                    Text(
                        text = "No Notifications Available",
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            // If there are notifications, show them in a LazyColumn
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(notificationList) { notification ->
                    NotificationItem(
                        notification = notification.notification
                    )
                }
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
    val emptyList = emptyList<Notification>()
    NotificationScreen(
        notificationList = emptyList
    )
}
