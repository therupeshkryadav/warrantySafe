package com.warrantysafe.app.presentation.ui.screens.home.components.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.presentation.ui.screens.common.productList.ProductList

@Composable
fun ExpiredTab(
    navController: NavController,
    expiredProducts: List<Product>
) {
//Tab Values
    Column(modifier = Modifier.fillMaxSize()) {
        ProductList(
            navController = navController,
            productList = expiredProducts
        )
    }
}

@Preview(showBackground = true)
@Composable
fun onPreview() {
    val expiredProducts = listOf(
        Product(
            title = "Rado Watch",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "PS5",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "LG Washing Machine ",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "Rado Watch",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "PS5",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "LG Washing Machine ",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "Rado Watch",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "PS5",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "LG Washing Machine ",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "Rado Watch",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "PS5",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product(
            title = "LG Washing Machine ",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        )
    )
    ExpiredTab(navController = rememberNavController(), expiredProducts = expiredProducts)
}