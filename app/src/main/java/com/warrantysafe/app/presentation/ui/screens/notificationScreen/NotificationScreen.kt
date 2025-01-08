package com.warrantysafe.app.presentation.ui.screens.notificationScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.warrantysafe.app.presentation.ui.screens.notificationScreen.components.NotificationItem
import com.warrantysafe.app.presentation.ui.screens.utils.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.viewModel.NotificationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotificationScreen(
    navController: NavController
) {
    val notificationViewModel: NotificationViewModel = koinViewModel()
    LaunchedEffect(Unit) {
        notificationViewModel.loadNotifications()
    }
    val notificationList = notificationViewModel.notifications.value

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
                    overflow = TextOverflow.Ellipsis,
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

        if (notificationList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding( vertical = 8.dp)
            ) {
                // Add "Mark All as Read" as the first item
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            ),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp),
                            imageVector = Icons.Default.Email,
                            contentDescription = "Mark All as Read"
                        )
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = "Mark all as Read",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                // Add notifications as list items
                items(notificationList) { notifications ->
                    NotificationItem(
                        notification = notifications.notification
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        } else {
            // Empty state UI
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "No Notifications",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(64.dp)
                )
                Text(
                    text = "No notifications to display.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}


//@Composable
//fun NotificationScreen(
//    navController: NavController
//) {
//    val notificationViewModel: NotificationViewModel = koinViewModel()
//
//    // Load notifications when the screen is launched
//    LaunchedEffect(Unit) {
//        notificationViewModel.loadNotifications()
//    }
//
//    val notificationList = notificationViewModel.notifications.value
//
//    Column(modifier = Modifier.fillMaxSize()) {
//        CustomTopAppBar(
//            title = {
//                Text(
//                    text = "Notifications",
//                    style = MaterialTheme.typography.titleLarge,
//                    textAlign = TextAlign.Center,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 16.sp,
//                    maxLines = 1,
//                    overflow = TextOverflow.Ellipsis,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .wrapContentHeight()
//                )
//            },
//            navigationIcon = {
//                IconButton(onClick = { navController.popBackStack() }) {
//                    Icon(
//                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                        contentDescription = "Navigate Back"
//                    )
//                }
//            },
//            actions = {}
//        )
//
//        // "Mark All as Read" Section
//        MarkAllAsReadSection()
//
//        // Notifications List or Empty State
//        if (notificationList.isNotEmpty()) {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(horizontal = 16.dp, vertical = 8.dp)
//            ) {
//                items(notificationList) { notifications ->
//                    NotificationItem(notification = notifications.notification)
//                }
//            }
//        } else {
//            EmptyState()
//        }
//    }
//}
//
//@Composable
//fun MarkAllAsReadSection() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//            .border(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)),
//        horizontalArrangement = Arrangement.Center,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Icon(
//            modifier = Modifier.padding(8.dp),
//            imageVector = Icons.Default.Email,
//            contentDescription = "Mark All as Read"
//        )
//        Text(
//            text = "Mark all as Read",
//            style = MaterialTheme.typography.bodyLarge,
//            modifier = Modifier.padding(8.dp)
//        )
//    }
//}
//
//@Composable
//fun EmptyState() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = "No notifications available.",
//            style = MaterialTheme.typography.bodyLarge,
//            textAlign = TextAlign.Center,
//            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
//        )
//    }
//}
