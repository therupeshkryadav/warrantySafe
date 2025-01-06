package com.warrantysafe.app.presentation.ui.screens.utils.sideDrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.ui.screens.utils.sideDrawer.components.SideDrawerItem

@Composable
fun SideDrawerContent(onItemClicked: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(280.dp)
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.Center),
                painter = painterResource(R.drawable.warranty_logo), // Replace with your logo or avatar
                contentDescription = "Drawer Header"
            )
        }

        HorizontalDivider(
            modifier = Modifier.padding(bottom = 16.dp),
            thickness = 1.dp,
            color = Color.Gray
        )

        // Inside your SideDrawerContent composable or wherever you're setting up the drawer
        listOf(
            "List of Product Cards" to R.drawable.list_product_card,
            "Help & Support" to R.drawable.help_support,
            "Rate and Review" to R.drawable.rate_review,
            "Share with Friends" to R.drawable.share_warranty,
            "Terms & Privacy" to R.drawable.policy_warranty,
            "About the App" to R.drawable.about_app,
            "Upcoming Features" to R.drawable.upcoming_features
        ).forEach { (item, iconRes) ->
            // Pass the item name, corresponding image resource, and the onClick handler
            SideDrawerItem(
                item = item,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                itemImg = iconRes,
                onClick = {
                    onItemClicked(item) // Calling onItemClicked with the item name (Settings or Logout)
                })
        }

        HorizontalDivider(
            modifier = Modifier.padding(top = 158.dp, bottom = 16.dp),
            thickness = 1.dp,
            color = Color.Gray
        )

        SideDrawerItem(
            item = "Settings",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            itemImg = R.drawable.settings,
            onClick = { onItemClicked("Settings") }
        )
    }
}

@Preview
@Composable
fun SideDrawerContentPreview() {
    SideDrawerContent(onItemClicked = {})
}