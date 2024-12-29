package com.warrantysafe.app.presentation.common.dropDownMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.presentation.common.dropDownMenu.components.dropDownMenuItem

@Composable
fun DropDownMenuContent(
    navController: NavController? = null, // NavController for navigation
    onItemClicked: () -> Unit, // Action on item click
    dropDownList: List<String>
) {
    Column(
        modifier = Modifier
            .wrapContentWidth().padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Navigation Items
        dropDownList.forEach { item ->
            dropDownMenuItem(
                item = item,
                onClick = { onItemClicked() }
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
        dropDownList = listOf("Logout"),
        onItemClicked = {}
    )
}
