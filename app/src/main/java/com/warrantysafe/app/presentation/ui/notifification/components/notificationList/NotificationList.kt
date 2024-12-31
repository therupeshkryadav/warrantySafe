package com.warrantysafe.app.presentation.ui.notifification.components.notificationList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.ui.notifification.Notifications
import com.warrantysafe.app.presentation.ui.notifification.components.NotificationItem

@Composable
fun NotificationList(
    notifications: List<Notifications> = emptyList() // Default to an empty list and Changed to a flat list of products
) {
    if (notifications.isEmpty()) {
        // Display the "no notifications" image
        Box(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(), // Make the Box take up the full available space
            contentAlignment = Alignment.TopCenter // Center the content inside the Box
        ) {
            Column(modifier = Modifier.fillMaxWidth(1f)) {
                Image(
                    painter = painterResource(id = R.drawable.not_found_notification),
                    contentDescription = "No notifications available",
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp)// Optional padding for spacing
                )
                Text(text = "No Notifications Available",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center)
            }
        }
    } else {
        // Display the notifications if the list is not empty
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(notifications) { notification ->
                NotificationItem(
                    isRead = notification.isRead,
                    notification = notification.notification
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewNotificationsList() {
    val notificationsList = emptyList<Notifications>()
    NotificationList(notifications = notificationsList)

}