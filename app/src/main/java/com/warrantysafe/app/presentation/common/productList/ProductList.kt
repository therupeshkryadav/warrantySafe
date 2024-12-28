package com.warrantysafe.app.presentation.common.productList

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.home.Product
import com.warrantysafe.app.presentation.common.productList.components.productCard.ProductCard
import com.warrantysafe.app.presentation.navgraph.Route


@Composable
fun ProductList(
    navController: NavController,
    productType: List<Product> // Changed to a flat list of products
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween, // Distribute components to start and end
            verticalAlignment = Alignment.CenterVertically // Center items vertically
        ) {
            // First Box (Sort By Section)
            Box(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surface)
                    .border(
                        width = 1.dp,
                        shape = RectangleShape,
                        color = colorResource(R.color.black)
                    )
                    .padding(start = 8.dp) // Padding for spacing within the Box
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically // Align items vertically
                ) {
                    Text(
                        text = "Sort By",
                        modifier = Modifier.padding(end = 4.dp) // Space between text and icon
                    )
                    Icon(
                        modifier = Modifier
                            .size(24.dp), // Define a consistent size for the icon
                        painter = painterResource(R.drawable.drop_down),
                        contentDescription = null
                    )
                }
            }

            // Second Box (Filter Section)
            Box(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surface)
                    .border(
                        width = 1.dp, shape = RectangleShape,
                        color = colorResource(R.color.black)
                    )
                    .padding(end = 8.dp) // Padding for spacing within the Box
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically // Align items vertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp) // Define a consistent size for the icon
                            .padding(start = 8.dp), // Space between icon and text
                        painter = painterResource(R.drawable.filter),
                        contentDescription = null
                    )
                    Text(
                        text = "Filter"
                    )
                }
            }

        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {
            items(productType) { product ->
                when (product) {
                    is Product.Active -> {
                        ProductCard(
                            title = product.title,
                            purchase = product.purchase,
                            expiry = product.expiry,
                            progress = product.progress,
                            imageResId = product.imageResId,
                            progressTint = colorResource(R.color.DaysLeft),
                            itemTint = colorResource(R.color.transparent),
                            detailsColor = MaterialTheme.colorScheme.onSurface,
                            onClick = { navigateToDetails(product, navController) }
                        )
                    }

                    is Product.Expired -> {
                        ProductCard(
                            title = product.title,
                            purchase = product.purchase,
                            expiry = product.expiry,
                            progress = product.progress,
                            progressTint = colorResource(R.color.noDaysLeft),
                            imageResId = product.imageResId,
                            itemTint = colorResource(R.color.expired),
                            detailsColor = MaterialTheme.colorScheme.inversePrimary,
                            onClick = { navigateToDetails(product, navController) }
                        )
                    }
                }
            }
        }
    }
}

private fun navigateToDetails(product: Product, navController: NavController) {

    val route = when (product) {
        is Product.Active -> Route.ProductDetailsScreen.createRoute(
            productName = product.title,  // Correct property name
            purchaseDate = product.purchase,
            expiryDate = product.expiry, // Placeholder for expiry logic
            progress = product.progress,
            period = product.period
        )

        is Product.Expired -> Route.ProductDetailsScreen.createRoute(
            productName = product.title,  // Correct property name
            purchaseDate = product.purchase,
            expiryDate = product.expiry, // Placeholder for expiry logic
            progress = product.progress,
            period = product.period
        )
    }
    Log.d("fatal", "Navigating to route: $route")
    navController.navigate(route)
}


@Preview(showBackground = true)
@Composable
fun PreviewProductList() {
    val activeProducts = listOf(
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
        )
    )

    ProductList(
        navController = rememberNavController(),
        productType = activeProducts
    )
}
