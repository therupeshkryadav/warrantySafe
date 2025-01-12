package com.warrantysafe.app.presentation.ui.screens.upcomingFeaturesScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UpcomingFeaturesItem(
    upcomingNotification: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(8.dp)
            .border(width = 1.dp, Color.Black)
            .background(Color.LightGray),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Text(
            modifier = Modifier.fillMaxWidth(0.7f).padding(horizontal = 32.dp, vertical = 8.dp),
            text = upcomingNotification,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            color = Color.Black
        )
        Box(modifier = Modifier.background(Color.Red)){
            Text(
                modifier = Modifier.padding(2.dp),
                text = "upcoming",
                fontSize = 8.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewNotificationItem() {
    UpcomingFeaturesItem(upcomingNotification = "Upcoming Feature")
}