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
import com.warrantysafe.app.presentation.common.dropDownMenu.components.dropDownMenuItem

@Composable
fun DropDownMenuContent(
    onItemClicked: (String) -> Unit
) {
    Column(
        modifier = Modifier.width(120.dp)
            .background(color = MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Navigation Items
        listOf("Share", "Rate us", "Privacy Policy", "Feedback").forEach { item ->
            dropDownMenuItem(item = item, onClick = onItemClicked)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDropDown() {
    DropDownMenuContent(
        onItemClicked = {}
    )
}