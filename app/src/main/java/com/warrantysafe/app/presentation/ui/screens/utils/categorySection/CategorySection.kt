package com.warrantysafe.app.presentation.ui.screens.utils.categorySection

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategorySection(
    updatedCategory: String,
    onSelectEnabled: Boolean,
    onCategoryChange: (String) -> Unit,
    onCategorySelection: () -> Unit
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = "Category",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Dropdown TextField
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .clickable(
                    enabled = onSelectEnabled,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null // Disables ripple effect
                ) { onCategorySelection() }
        ) {
            TextField(
                value = updatedCategory,
                onValueChange = onCategoryChange, // No need for manual input, selection happens from dropdown
                placeholder = {
                    Text(text = "Select a category", color = Color.Gray)
                },
                readOnly = true,
                enabled = false, // Prevent typing
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 8.dp),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown icon"
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledContainerColor = Color.White,
                    unfocusedTextColor = Color.Black,
                    disabledTextColor = Color.Black
                ),
                textStyle = TextStyle(fontSize = 16.sp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RedesignedCategorySectionPreview() {
    val selectedCategory by remember { mutableStateOf("") }

    CategorySection(
        updatedCategory = selectedCategory,
        onSelectEnabled = true,
        onCategoryChange = {},
        onCategorySelection = {}
    )
}

