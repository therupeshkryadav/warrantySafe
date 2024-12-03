package com.warrantysafe.app.presentation.profile.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.warrantysafe.app.R

@Composable
fun ProfileDetailRow(label: String, value: String, textColor: Color, borderColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(vertical = 8.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxHeight(1f)
                .align(Alignment.CenterVertically).padding(top = 8.dp,end=8.dp),
            text = "$label:",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = colorResource(R.color.black)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .border(1.dp, borderColor)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 6.dp),
                text = value,
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                color = textColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileDetailRowPreview() {
    ProfileDetailRow(
        label = "Email",
        value = "example@example.com",
        textColor = colorResource(R.color.black),
        borderColor = colorResource(R.color.purple_500)
    )
}

