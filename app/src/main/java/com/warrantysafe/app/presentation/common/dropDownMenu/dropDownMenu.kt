package com.warrantysafe.app.presentation.common.dropDownMenu

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.common.dropDownMenu.components.dropDownItem

@SuppressLint("ComposableNaming")
@Composable
fun dropDownMenu(
    menuItems: List<String>,
    isExpanded: MutableState<Boolean>,
    onItemClicked: (String) -> Unit
) {
    DropdownMenu(
        expanded = isExpanded.value,
        onDismissRequest = { isExpanded.value = false },
        modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(8.dp))
    ) {
        menuItems.forEach { item ->
            dropDownItem(
                item = item,
                onClick = {
                    onItemClicked(item) // Handle menu item click
                    isExpanded.value = false // Close the dropdown menu
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DropDownMenuPreview() {
    var isMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .padding(2.dp)
    ) {paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .background(color = colorResource(R.color.xtreme2)),
            contentAlignment = Alignment.CenterEnd
        ) {
            // Button to toggle dropdown menu visibility
            IconButton(onClick = { isMenuExpanded = !isMenuExpanded }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "More options"
                )
            }

            dropDownMenu(
                menuItems = listOf("Home", "Profile", "Settings", "Logout"),
                isExpanded = remember { mutableStateOf(isMenuExpanded) },
                onItemClicked = { selectedItem ->
                    // Handle item click, can add actions here
                    println("Selected Item: $selectedItem")
                }
            )
        }
    }
}
