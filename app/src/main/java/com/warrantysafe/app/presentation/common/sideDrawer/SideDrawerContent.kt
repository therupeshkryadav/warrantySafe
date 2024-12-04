package com.warrantysafe.app.presentation.common.sideDrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.common.sideDrawer.components.SideDrawerItem

@Composable
fun SideDrawerContent(onItemClicked: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(280.dp)
            .background(color = colorResource(R.color.white))
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .statusBarsPadding()
                .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.item_image_placeholder), // Replace with your logo or avatar
                contentDescription = "Drawer Header"
            )
        }

        Divider(modifier = Modifier.padding(bottom = 16.dp), color = Color.Gray, thickness = 1.dp)

        // Navigation Items
        listOf("Home", "Profile", "Settings", "Logout").forEach { item ->
            SideDrawerItem(item = item, onClick = onItemClicked)
        }
    }
}

@Preview
@Composable
fun SideDrawerContentPreview(){
    SideDrawerContent(onItemClicked = {})
}