package com.warrantysafe.app.presentation.ui.screens.main.profileScreen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
    placeHolder: String = "",
    updatedValue: String,
    onValueChange: (String) -> Unit, // New parameter to update the value dynamically
    onDetailRowClick: (() -> Unit)? = null // Optional callback for icon clicks
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
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
                placeholder = { Text( text = placeHolder, color = Color.LightGray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(20.dp)
                    ),
                textStyle = TextStyle(fontSize = 16.sp),
                singleLine = true,
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.LightGray,
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
        icon = R.drawable.calendar,
        onDetailRowClick = { },
        onValueChange = { }
    )
}
