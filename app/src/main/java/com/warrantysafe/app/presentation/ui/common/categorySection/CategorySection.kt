package com.warrantysafe.app.presentation.ui.common.categorySection

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.ui.common.dropDownMenu.DropDownMenuContent
import com.warrantysafe.app.presentation.ui.common.dropDownMenu.components.dropDownMenuItem

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun CategorySection(
    selectCategory: String? = "General",
    enabled: Boolean
) {
    // List of categories
    val categories = listOf(
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
    )

    // State for dropdown menu
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf(selectCategory ?: "General") } // Fallback to "General" if null

    // Category Section Layout
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .padding(end = 8.dp),
            text = "Category",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.purple_500) // Replace with colorResource if needed
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(bottom = 8.dp)
                .clip(RoundedCornerShape(2.dp))
                .clickable(enabled = enabled) { expanded = !expanded }
                .border(width = 1.dp, color = Color.Black) // Replace with colorResource if needed
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color.LightGray) // Replace with desired color
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    text = selectedCategory,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )
                Icon(
                    modifier = Modifier
                        .width(24.dp)
                        .fillMaxHeight(),
                    painter = painterResource(id = R.drawable.drop_down), // Replace with your dropdown icon
                    contentDescription = null
                )
            }
        }

        // Dropdown Menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = Color.LightGray,
            modifier = Modifier.fillMaxWidth()
        ) {
            categories.forEach { category ->
                dropDownMenuItem(
                    onClick = {
                        selectedCategory = category // Update selected category
                        expanded = false // Close dropdown menu
                    },
                    item = category
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CategorySectionPreview() {
    CategorySection(enabled = true)
}
