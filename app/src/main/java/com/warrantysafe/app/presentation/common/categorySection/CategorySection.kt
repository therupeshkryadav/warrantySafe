package com.warrantysafe.app.presentation.common.categorySection

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.warrantysafe.app.R // Make sure to import your resources if needed

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun CategorySection() {
    // List of categories
    val categories = listOf("General", "Electronics", "Furniture", "Appliances", "Others")

    // State for dropdown menu
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("General") }


    // Category Section Layout
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .padding(end = 8.dp),
                text = "Category :",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black // Replace with colorResource if needed
            )

            Box( // General Box
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .clickable { expanded = !expanded }
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
                            .fillMaxHeight(1f),
                        painter = painterResource(id = R.drawable.drop_down), // Replace with your dropdown icon
                        contentDescription = null
                    )
                }
            }
        }

        // Dropdown Menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = Color.LightGray,
            properties = PopupProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                clippingEnabled = true
            ),
            modifier = Modifier.wrapContentHeight().fillMaxWidth(0.6f)
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    onClick = {
                        selectedCategory = category
                        expanded = false
                    },
                    text = { Text(text = category) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategorySectionPreview() {
    CategorySection()
}
