package com.warrantysafe.app.presentation.ui.screens.homeScreen.tabs

import android.util.Log
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.productCardList.applySorting
import com.warrantysafe.app.presentation.ui.screens.productCardList.components.ProductCard
import com.warrantysafe.app.presentation.ui.screens.utils.dropDownMenu.components.dropDownMenuItem

@Composable
fun ActiveTab(
    navController: NavController,
    activeProducts: List<Product>
) {
    val sortOptions = listOf(
        "Old to Recent",
        "Recent to Old"
    )
    val expandedSort = remember { mutableStateOf(false) }
    val selectedSortOption = remember { mutableStateOf("Sort By") }
    Column(modifier = Modifier.fillMaxSize()) {
        if (activeProducts.isNotEmpty()) {
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
                                    applySorting(option, activeProducts) // Sorting logic
                                }
                            )
                        }
                    }
                }
            }
            //Tab Values
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
            ) {
                items(activeProducts) { product ->
                    ProductCard(
                        productName = product.productName,
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
        } else {
            // Empty state UI
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "No Products",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(64.dp)
                )
                Text(
                    text = "No Active Products, Add to display.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }


    }
}

private fun navigateToDetails(product: Product, navController: NavController) {

    val route = Route.ProductDetailsScreen.createRoute(
        productName = product.productName,  // Correct property name
        purchaseDate = product.purchase,
        category = product.category,
        expiryDate = product.expiry,
        notes = product.notes
    ) // Placeholder for expiry logic
    Log.d("fatal", "Navigating to route: $route")
    navController.navigate(route)
}
