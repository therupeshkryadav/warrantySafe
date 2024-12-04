package com.warrantysafe.app.presentation.common.dropDownMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.common.dropDownMenu.components.dropDownMenuItem

@Composable
fun DropDownMenuContent(
    modifier: Modifier = Modifier,
    onItemClicked: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .background(color = colorResource(R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Navigation Items
        listOf("Home", "Profile", "Settings", "Logout").forEach { item ->
            dropDownMenuItem(item = item, onClick = onItemClicked)
        }
    }
}

@Preview
@Composable
fun PreviewDropDown(){
    DropDownMenuContent(
        modifier = Modifier,
        onItemClicked = {}
    )
}