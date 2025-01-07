package com.warrantysafe.app.presentation.ui.screens.utils.categorySection

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.warrantysafe.app.R

@Composable
fun CategorySection(
    selectCategory: String?=null,
) {
    var expanded by remember { mutableStateOf(false) } // Control the dropdown visibility
    val categoryOptions = listOf(
        "General",
        "Electronics",
        "Vehicles",
        "Furniture",
        "Home Appliances",
        "Kitchen Appliances",
        "Gadgets & Accessories",
        "Personal & Lifestyle Products",
        "Tools & Equipment",
        "Health & Medical Devices",
        "Wearables",
        "Others"
    ) // Dropdown options
    val selectedCategory = remember { mutableStateOf(selectCategory?: "Select Category")} // Fallback to "General" if null

    // Category Section Layout
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 4.dp),
            text = "Category",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray // Replace with colorResource if needed
        )

        // Type Box - Clickable area to toggle the dropdown
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(width = 1.dp, color = Color.LightGray)
                .padding(16.dp)
                .clickable(
                    indication = null, // Remove ripple effect
                    interactionSource = remember { MutableInteractionSource() } // Provide an interaction source
                ) { expanded = !expanded },
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = selectedCategory.value,
                fontSize = 16.sp,
                color = Color.Black
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Arrow Down",
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }

        // Dropdown List - Show when expanded is true
        if (expanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(8.dp)
            ) {
                categoryOptions.forEach { category ->
                    Text(
                        text = category,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                selectedCategory.value = category // Set the selected category
                                expanded = false // Close the dropdown after selection
                            },
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CategorySectionPreview() {
    CategorySection()
}
