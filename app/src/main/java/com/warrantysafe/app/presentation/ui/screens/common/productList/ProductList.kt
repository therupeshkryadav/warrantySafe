package com.warrantysafe.app.presentation.ui.screens.common.productList

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.warrantysafe.app.presentation.ui.screens.common.dropDownMenu.components.dropDownMenuItem
import com.warrantysafe.app.presentation.ui.screens.common.productList.components.productCard.ProductCard
import com.warrantysafe.app.presentation.ui.screens.home.Product
import com.warrantysafe.app.presentation.navigation.Route


@Composable
fun ProductList(
    navController: NavController,
    productType: List<Product> // Changed to a flat list of products
) {
    val sortOptions = listOf(
        "Old to Recent",
        "Recent to Old"
    )
    val expandedSort = remember { mutableStateOf(false) }
    val selectedSortOption = remember { mutableStateOf("Sort By") }


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
                    .clickable { expandedSort.value = true }
                    .border(
                        width = 1.dp,
                        shape = RectangleShape,
                        color = colorResource(R.color.black)
                    )
                    .padding(start = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Sort By",
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(R.drawable.drop_down),
                        contentDescription = null
                    )
                }

                DropdownMenu(
                    expanded = expandedSort.value,
                    onDismissRequest = { expandedSort.value = false }
                ) {
                    sortOptions.forEach { option ->
                        dropDownMenuItem(
                            item = option,
                            onClick = {
                                selectedSortOption.value = option
                                expandedSort.value = false
                                applySorting(option, productType) // Sorting logic
                            }
                        )
                    }
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
                            category = product.category,
                            imageResId = product.imageResId,
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
                            category = product.category,
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

fun applySorting(option: String, products: List<Product>) {
    when (option) {
        "By Date of Purchase" -> {
            // Sorting logic for date of purchase
        }
        "By Days Left in Expiry" -> {
            // Sorting logic for days left in expiry
        }
        "By Name of Product" -> {
            // Sorting logic for product name
        }
        "By Category" -> {
            // Sorting logic for category
        }
    }
}

private fun navigateToDetails(product: Product, navController: NavController) {

    val route = when (product) {
        is Product.Active -> Route.ProductDetailsScreen.createRoute(
            productName = product.title,  // Correct property name
            purchaseDate = product.purchase,
            category = product.category,
            expiryDate = product.expiry, // Placeholder for expiry logic
        )

        is Product.Expired -> Route.ProductDetailsScreen.createRoute(
            productName = product.title,  // Correct property name
            purchaseDate = product.purchase,
            category = product.category,
            expiryDate = product.expiry, // Placeholder for expiry logic
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

    ProductList(
        navController = rememberNavController(),
        productType = activeProducts
    )
}
