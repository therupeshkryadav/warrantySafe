package com.warrantysafe.app.presentation.ui.screens.notifificationScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.domain.model.Notification
import com.warrantysafe.app.presentation.ui.screens.utils.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.ui.screens.notifificationScreen.components.NotificationItem

@Composable
fun NotificationScreen(
    notificationList: List<Notification>,
    navController: NavController
) {

    // Content under the TopAppBar
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomTopAppBar(
            title = {
                Text(
                    text = "Notification Screen",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,  // Handling overflow text
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            actions = {}
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(notificationList) { notifications ->
                NotificationItem(
                    notification = notifications.notification
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
        navController = rememberNavController(),
        notificationList = notifications
    )
}
