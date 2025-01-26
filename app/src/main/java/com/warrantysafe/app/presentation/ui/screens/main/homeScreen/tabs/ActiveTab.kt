package com.warrantysafe.app.presentation.ui.screens.main.homeScreen.tabs

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.utils.Results
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.productCardList.components.ProductCard
import com.warrantysafe.app.presentation.ui.screens.main.utils.dropDownMenu.components.dropDownMenuItem
import com.warrantysafe.app.presentation.viewModel.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ActiveTab(
    navController: NavController
) {
    val productViewModel: ProductViewModel = koinViewModel()

    // Observe the result state
    val productState by productViewModel.activeProductsState.observeAsState(initial = Results.Loading)

    LaunchedEffect(Unit){
        productViewModel.loadActiveProducts()
    }

    val sortOptions = listOf(
        "Old to Recent",
        "Recent to Old"
    )
    val expandedSort = remember { mutableStateOf(false) }
    val selectedSortOption = remember { mutableStateOf("Sort By") }

    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp)) {
        when (val state = productState) {
            is Results.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is Results.Success -> {
                val activeProducts = state.data
                if (activeProducts.isEmpty()) {
                    // Empty state when the list is fetched but contains no products
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
                            text = "No Active Products Found. Add some to display!",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }
                } else {
                    // Sort By Section
                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(bottom = 4.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null // Disables ripple effect
                            ) { expandedSort.value = true }
                            .border(
                                width = 1.dp,
                                shape = RectangleShape,
                                color = colorResource(R.color.black)
                            )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = selectedSortOption.value,
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(R.drawable.drop_down),
                                contentDescription = null
                            )
                        }

                        DropdownMenu(
                            modifier = Modifier.wrapContentWidth(),
                            containerColor = Color.White,
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

                    // Displaying Products
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        items(activeProducts) { product ->
                            ProductCard(
                                onClick = { navigateToDetails(product, navController) },
                                onSlidingForward = {},
                                productName = product.productName,
                                itemTint = Color.Transparent,
                                category = product.category,
                                detailsColor = Color.Black,
                                purchase = product.purchase,
                                expiry = product.expiry,
                                imageResource = rememberAsyncImagePainter(product.productImageUri),
                                onSlidingBackward = {}
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(68.dp))
                        }
                    }
                }
            }

            is Results.Failure -> {
                // Error UI
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "An error occurred: ${state.exception}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

fun applySorting(option: String, products: List<Product>): List<Product> {
    return when (option) {
        "Old to Recent" -> products.sortedBy { it.purchase }
        "Recent to Old" -> products.sortedByDescending { it.purchase }
        else -> products // Default sorting (if needed)
    }
}

private fun navigateToDetails(product: Product, navController: NavController) {
    val route = Route.ProductDetailsScreen.createRoute(
        id = product.id
    )
    Log.d("Navigation", "Navigating to route: $route")
    navController.navigate(route)
}
