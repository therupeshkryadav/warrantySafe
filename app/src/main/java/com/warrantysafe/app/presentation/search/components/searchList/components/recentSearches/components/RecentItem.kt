package com.warrantysafe.app.presentation.search.components.searchList.components.recentSearches.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.warrantysafe.app.R

@Composable
fun RecentItem(
    recent : String
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween) {
        Text(
            text = recent,
            color = colorResource(R.color.teal_700),
            modifier = Modifier
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.End,
            fontSize = 12.sp,
            fontWeight = FontWeight.W300
        )
        Image(
            modifier = Modifier.padding(start = 16.dp),
            painter = painterResource(R.drawable.recent_search_img),
            contentDescription = null
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRecentItem() {
    RecentItem("notification")
}