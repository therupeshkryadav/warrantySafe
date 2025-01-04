package com.warrantysafe.app.presentation.ui.screens.notifification.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.warrantysafe.app.R

@Composable
fun NotificationItem(
    notification: String
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .border(width = 1.dp, color = colorResource(R.color.xtreme))
        .background(color = colorResource(R.color.transparent))
        .clickable { onNotificationClick() }) {
        Text(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            text = notification)
    }

}

fun onNotificationClick() {
    //change the background color to colorResource(R.color.xtreme2)
}

@Preview
@Composable
private fun PreviewNotificationItem() {
    NotificationItem(notification = "My First Notification!!")

}