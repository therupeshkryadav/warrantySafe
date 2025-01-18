package com.warrantysafe.app.presentation.ui.screens.main.utils.dropDownMenu.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.warrantysafe.app.R

@SuppressLint("ComposableNaming")
@Composable
fun dropDownMenuItem(
    item: String,
    onClick: () -> Unit = {}) {

    Row(
        modifier = Modifier.wrapContentHeight().padding( horizontal = 4.dp).clickable { onClick() }
    ) {
        if(item == "Logout"){
        Icon(
            painter = painterResource(R.drawable.logout), // Replace with relevant icons
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(21.dp)
        )
        }

        Text(
            modifier = Modifier.wrapContentWidth(),
            text = item,
            textAlign = TextAlign.Start,
            maxLines = 1,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }

}

@Preview(showBackground = true)
@Composable
fun dropDownItemPreview() {
    dropDownMenuItem("Logout", onClick = {})
}