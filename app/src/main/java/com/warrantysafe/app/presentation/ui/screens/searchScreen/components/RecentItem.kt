package com.warrantysafe.app.presentation.ui.screens.searchScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.Recent

@Composable
fun RecentItem(
    recentItem : Recent
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp, vertical = 8.dp)) {
        Image(
            modifier = Modifier.padding(horizontal = 16.dp).size(20.dp).align(Alignment.CenterVertically),
            painter = painterResource(R.drawable.recent_1),
            contentDescription = null
        )
        Text(
            text = recentItem.recent,
            color = Color.DarkGray,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Start,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Image(
            modifier = Modifier.padding(horizontal = 16.dp).size(20.dp).align(Alignment.CenterVertically),
            painter = painterResource(R.drawable.recent_2),
            contentDescription = null
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRecentItem() {
    RecentItem(recentItem = Recent("notification"))
}