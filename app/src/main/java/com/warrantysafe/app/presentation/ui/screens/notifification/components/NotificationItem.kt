package com.warrantysafe.app.presentation.ui.screens.notifification.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.warrantysafe.app.R

@Composable
fun NotificationItem(
    notification: String
) {
    // Track if the item is clicked or not
    val isClicked = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = colorResource(R.color.xtreme))
            .background(color = if (isClicked.value) Color.Transparent else colorResource(R.color.xtreme2))
            .clickable(
                interactionSource = remember {MutableInteractionSource()}, // Disable the ripple effect
                indication = null,
                onClick = {
                    if (!isClicked.value) {
                        isClicked.value = true // Change background once clicked
                    }
                }
            )
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            text = notification
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewNotificationItem() {
    NotificationItem(
        notification = "My First Notification!!"
    )
}
