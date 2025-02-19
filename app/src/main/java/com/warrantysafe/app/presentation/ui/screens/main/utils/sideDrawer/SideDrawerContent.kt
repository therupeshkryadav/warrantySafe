package com.warrantysafe.app.presentation.ui.screens.main.utils.sideDrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
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
import com.warrantysafe.app.presentation.ui.screens.main.utils.sideDrawer.components.SideDrawerItem

@Composable
fun SideDrawerContent(
    modifier: Modifier,
    onItemClicked: (String) -> Unit
) {
    Column(
        modifier = modifier
            .width(280.dp) // Fixed width for the drawer
            .systemBarsPadding()
            .statusBarsPadding()
            .border(width = 1.dp, Color.Black)
            .background(color = Color.White)
    ) {
        // Header
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), // Header takes a fixed height
            alignment = Alignment.Center,
            painter = painterResource(R.drawable.warranty_logo),
            contentDescription = null
        )

        HorizontalDivider(
            modifier = Modifier.padding(bottom = 16.dp),
            thickness = 1.dp,
            color = Color.Gray
        )

        // Content inside the side drawer
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f) // This makes the column take up the remaining space
        ) {
            listOf(
                "List of Product Cards" to R.drawable.list_product_card,
                "Help & Support" to R.drawable.help_support,
                "Rate and Review" to R.drawable.rate_review,
                "Share with Friends" to R.drawable.share_warranty,
                "Terms & Conditions" to R.drawable.policy_warranty,
                "Privacy Policy" to R.drawable.terms_warranty,
                "About the App" to R.drawable.about_app,
                "Upcoming Features" to R.drawable.upcoming_features,
                "Settings" to R.drawable.settings
            ).forEach { (item, iconRes) ->
                SideDrawerItem(
                    item = item,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    itemImg = iconRes,
                    onClick = {
                        onItemClicked(item)
                    })
            }
        }
    }
}

@Preview
@Composable
fun SideDrawerContentPreview() {
    SideDrawerContent(modifier = Modifier.fillMaxHeight(1f), onItemClicked = {})
}
