package com.warrantysafe.app.presentation.profile.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.warrantysafe.app.R

@Composable
fun DetailRow(
    label: String,
    initialValue: String,
    enable: Boolean,
    textColor: Color,
    borderColor: Color,
    @DrawableRes icon: Int? = null // Default value is null
) {
    // State to manage the editable value
    val editableValue = remember { mutableStateOf(initialValue) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically // Ensures proper alignment of text and field
    ) {
        Text(
            modifier = Modifier
                .padding(end = 8.dp), // Padding between label and text field
            text = "$label:",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = colorResource(R.color.black)
        )
        Box(
            modifier = Modifier
                .border(1.dp, borderColor)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 8.dp) // Padding inside the box for better visuals
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = editableValue.value,
                    onValueChange = { editableValue.value = it },
                    modifier = Modifier
                        .weight(0.6f)
                        .wrapContentHeight(),
                    enabled = enable,
                    textStyle = LocalTextStyle.current.copy(
                        color = textColor,
                        fontSize = 16.sp
                    ),
                    visualTransformation = VisualTransformation.None,
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedTextColor = textColor,
                        focusedTextColor = textColor
                    )
                )
                if (icon != null) { // Only render Icon if icon is not null
                    Icon(
                        modifier = Modifier
                            .width(24.dp)
                            .align(Alignment.CenterVertically),
                        painter = painterResource(icon),
                        contentDescription = null
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailRowPreview() {
    DetailRow(
        label = "Email",
        initialValue = "example@example.com",
        textColor = colorResource(R.color.black),
        enable = false,
        icon = R.drawable.calendar,
        borderColor = colorResource(R.color.purple_500)
    )
}
