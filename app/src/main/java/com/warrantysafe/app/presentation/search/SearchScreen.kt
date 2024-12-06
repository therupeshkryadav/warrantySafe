package com.warrantysafe.app.presentation.search

import com.warrantysafe.app.presentation.search.components.SearchList
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.home.Product

@Composable
fun SearchScreen(navController: NavController) {
    // Content under the TopAppBar
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val matchedList = listOf(
            Product.Active(
                title = "Realme 3 Pro",
                purchase = "30/11/2024",
                expiry = "",
                period = "1 year 0 months 0 days",
                progress = 0.7f,
                imageResId = R.drawable.item_image_placeholder
            ),
            Product.Active(
                title = "Realme 7 Pro",
                purchase = "30/11/2024",
                expiry = "",
                period = "1 year 0 months 0 days",
                progress = 0.7f,
                imageResId = R.drawable.item_image_placeholder
            ),
            Product.Active(
                title = "Redmi Note 10 ",
                purchase = "30/11/2024",
                expiry = "",
                period = "1 year 0 months 0 days",
                progress = 0.7f,
                imageResId = R.drawable.item_image_placeholder
            ),
            Product.Expired(
                title = "Rado Watch",
                purchase = "30/11/2024",
                expiry = "",
                period = "0 year 0 months 0 days",
                progress = 1f,
                imageResId = R.drawable.item_image_placeholder
            ),
            Product.Expired(
                title = "PS5",
                purchase = "30/11/2024",
                expiry = "",
                period = "0 year 0 months 0 days",
                progress = 1f,
                imageResId = R.drawable.item_image_placeholder
            ),
            Product.Expired(
                title = "LG Washing Machine ",
                purchase = "30/11/2024",
                expiry = "",
                period = "0 year 0 months 0 days",
                progress = 1f,
                imageResId = R.drawable.item_image_placeholder
            )
        )
        SearchList(navController = navController, matchedList = matchedList)
    }
}

@Preview
@Composable
fun PreviewSearchScreen() {
    SearchScreen(rememberNavController())
}
