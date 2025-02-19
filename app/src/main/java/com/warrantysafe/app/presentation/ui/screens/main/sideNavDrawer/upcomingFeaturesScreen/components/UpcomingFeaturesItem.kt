package com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.upcomingFeaturesScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun UpcomingFeaturesItem(upcomingNotification: String) {
    var alpha by remember { mutableStateOf(1f) }

    LaunchedEffect(Unit) {
        while (true) {
            alpha = 0f
            delay(500)
            alpha = 1f
            delay(500)
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEEEEEE)) // Light stylish gray
            .padding(8.dp),
    ) {
        Text(
            text = upcomingNotification,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.weight(1f).padding(end = 2.dp)
        )

        Box(
            modifier = Modifier
                .wrapContentWidth()
                .background(Color.Red, shape = RoundedCornerShape(8.dp))
                .alpha(alpha) ,// Apply flickering effect
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "UPCOMING",
                fontSize = 8.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 0.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewNotificationItem() {
    UpcomingFeaturesItem(upcomingNotification = "Upcoming Feature")
}