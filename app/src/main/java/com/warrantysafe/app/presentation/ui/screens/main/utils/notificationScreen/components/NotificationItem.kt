package com.warrantysafe.app.presentation.ui.screens.main.utils.notificationScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.warrantysafe.app.domain.model.Notification

@Composable
fun NotificationItem(
    notification: String,
    isRead: Boolean,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = if (isRead) Color.Transparent else MaterialTheme.colorScheme.background)
            .padding(vertical = 8.dp)
            .clickable(
                interactionSource = remember {MutableInteractionSource()}, // Disable the ripple effect
                indication = null,
                onClick = { onClick() }
            )
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp, vertical = 8.dp),
            text = notification,
            textAlign = TextAlign.Start,
            color = Color.Black
        )
    }


}

@Preview(showBackground = true)
@Composable
private fun PreviewNotificationItem() {
    NotificationItem(
        notification = "My First Notification!!",
        isRead = false,
        onClick = {}
    )
}
