package com.warrantysafe.app.presentation.ui.screens.home.components.tabs

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.common.productList.ProductList
import com.warrantysafe.app.presentation.ui.screens.common.productList.components.ProductCard

@Composable
fun ExpiredTab(
    navController: NavController,
    expiredProducts: List<Product>
) {
    //Tab Values
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(expiredProducts) { product ->
            ProductCard(
                title = product.title,
                purchase = product.purchase,
                expiry = product.expiry,
                category = product.category,
                imageResId = product.imageResId,
                itemTint = colorResource(R.color.transparent),
                detailsColor = MaterialTheme.colorScheme.onSurface,
                onClick = { navigateToDetails(product, navController) }
            )
        }
    }
}

private fun navigateToDetails(product: Product, navController: NavController) {

    val route = Route.ProductDetailsScreen.createRoute(
        productName = product.title,  // Correct property name
        purchaseDate = product.purchase,
        category = product.category,
        expiryDate = product.expiry) // Placeholder for expiry logic
    Log.d("fatal", "Navigating to route: $route")
    navController.navigate(route)
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