package com.warrantysafe.app.presentation.ui.screens.home.components.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun ExpiredTab(
    navController: NavController,
    expiredProducts: List<Product>
) {
//Tab Values
    Column(modifier = Modifier.fillMaxSize()) {
        ProductList(
            navController = navController,
            productType = expiredProducts
        )
    }
}

@Preview(showBackground = true)
@Composable
fun onPreview() {
    val expiredProducts = listOf(
        Product.Expired(
            title = "Rado Watch",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "PS5",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "LG Washing Machine ",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "Rado Watch",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "PS5",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "LG Washing Machine ",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "Rado Watch",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "PS5",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "LG Washing Machine ",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "Rado Watch",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "PS5",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "LG Washing Machine ",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        )
    )
    ExpiredTab(navController = rememberNavController(), expiredProducts = expiredProducts)
}