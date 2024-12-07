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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.warrantysafe.app.R

@SuppressLint("ComposableNaming")
@Composable
fun dropDownMenuItem(item: String, onClick: (String) -> Unit) {

    Row(
        modifier = Modifier.wrapContentHeight().padding( horizontal = 4.dp)
            .background(color = colorResource(R.color.transparent))
    ) {
        Icon(
            painter = painterResource(R.drawable.logout), // Replace with relevant icons
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(21.dp)
        )
        Text(
            modifier = Modifier.wrapContentHeight(),
            text = item,
            textAlign = TextAlign.Center,
            maxLines = 1,
            fontSize = 18.sp,
            color = Color.Black
        )
    }

}

@Preview(showBackground = true)
@Composable
fun dropDownItemPreview() {
    dropDownMenuItem("Home", onClick = {})
}