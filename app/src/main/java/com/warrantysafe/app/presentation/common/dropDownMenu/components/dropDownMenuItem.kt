package com.warrantysafe.app.presentation.common.dropDownMenu.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.warrantysafe.app.R

@SuppressLint("ComposableNaming")
@Composable
fun dropDownMenuItem(item: String, onClick: (String) -> Unit) {

    Box(
        modifier = Modifier
            .background(color = colorResource(R.color.transparent))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .padding(horizontal = 8.dp)
                .clickable { onClick(item) },
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_launcher_foreground), // Replace with relevant icons
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(36.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .padding(top = 6.dp),
                text = item,
                fontSize = 18.sp,
                color = Color.Black
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun dropDownItemPreview() {
    dropDownMenuItem("Home", onClick = {})
}