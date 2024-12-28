package com.warrantysafe.app.presentation.common.dropDownMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.presentation.common.dropDownMenu.components.dropDownMenuItem
import com.warrantysafe.app.presentation.navgraph.Route

@Composable
fun DropDownMenuContent(
    navController: NavController, // NavController for navigation
    onItemClicked: () -> Unit // Action on item click
) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .background(color = MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Navigation Items
        listOf("Logout").forEach { item ->
            dropDownMenuItem(
                item = item,
                onClick = {
                    if (item == "Logout") {
                        
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDropDown() {
    // Preview without real NavController
    DropDownMenuContent(
        navController = rememberNavController(),
        onItemClicked = {}
    )
}
