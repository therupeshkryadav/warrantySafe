package com.warrantysafe.app.presentation.ui.screens.home.components.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.ui.screens.common.productList.ProductList
import com.warrantysafe.app.presentation.ui.screens.home.Product

@Composable
fun ActiveTab(
    navController: NavController,
    activeProducts: List<Product>
) {
    //Tab Values
    Column(modifier = Modifier.fillMaxSize()) {
        ProductList(
            navController = navController,
            productType = activeProducts
        )
    }
}

@Preview
@Composable
private fun PrevActive() {
    val activeProducts = listOf(
        Product.Active(
            title = "Realme 3 Pro",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Realme 7 Pro",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Redmi Note 10 ",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Realme 3 Pro",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Realme 7 Pro",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Redmi Note 10 ",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Realme 3 Pro",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Realme 7 Pro",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Redmi Note 10 ",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Realme 3 Pro",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Realme 7 Pro",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Redmi Note 10 ",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        )

    )
    ActiveTab(navController = rememberNavController(), activeProducts = activeProducts)
}