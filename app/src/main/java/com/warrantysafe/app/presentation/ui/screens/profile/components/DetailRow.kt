package com.warrantysafe.app.presentation.ui.screens.profile.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.warrantysafe.app.R

@Composable
fun DetailRow(
    label: String,
    textColor: Color,
    enable: Boolean,
    icon: Int? = null,
    borderColor: Color,
    placeHolder: String = "",
    updatedValue: String,
    onValueChange: (String) -> Unit, // New parameter to update the value dynamically
    onDetailRowClick: (() -> Unit)? = null // Optional callback for icon clicks
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(1.dp, borderColor, RoundedCornerShape(4.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = label,
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null // Disables ripple effect
                ) {
                    onDetailRowClick?.invoke()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Optional Icon
            icon?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            // TextField to enter or update value
            TextField(
                value = updatedValue,
                onValueChange = onValueChange, // Update the value dynamically
                enabled = enable,
                placeholder = { Text(text = placeHolder) },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp),
                textStyle = TextStyle(fontSize = 16.sp),
                singleLine = true,
                shape = RectangleShape,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.LightGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.LightGray,
                    disabledContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black,
                    disabledTextColor = Color.Black
                )
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailRowPreview() {
    DetailRow(
        label = "Purchase Date",
        updatedValue = "23/11/2024",
        enable = false,
        textColor = colorResource(R.color.black),
        placeHolder = "DD/MM/YYYY",
        borderColor = colorResource(R.color.purple_500),
        icon = R.drawable.calendar,
        onDetailRowClick = { },
        onValueChange = { }
    )
}
