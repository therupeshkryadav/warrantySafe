package com.warrantysafe.app.presentation.ui.screens.main.homeScreen.tabs

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.utils.Results
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.productCardList.components.ProductCard
import com.warrantysafe.app.presentation.viewModel.ProductViewModel
import com.warrantysafe.app.utils.checkValidNetworkConnection
import org.koin.androidx.compose.koinViewModel

@Composable
fun ActiveTab(
    navController: NavController
) {
    val context = LocalContext.current
    val productViewModel: ProductViewModel = koinViewModel()

    // Observe the result state
    val productState by productViewModel.activeProductsState.observeAsState()

    // Remember and update internet connection status dynamically
    val isConnected = remember { mutableStateOf(checkValidNetworkConnection(context)) }
    LaunchedEffect(Unit) {
        productViewModel.loadActiveProducts()
    }
    // Continuously monitor the network status
    LaunchedEffect(isConnected.value) {
        if (!isConnected.value) {
            Toast.makeText(context, "No Valid Internet Connection!!", Toast.LENGTH_LONG).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        when (val state = productState) {
            is Results.Loading -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .statusBarsPadding()
                        .navigationBarsPadding(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Fetching Active Products...")
                    LinearProgressIndicator()
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
                    val sortOptions = listOf("Old to Recent", "Recent to Old")
                    val expandedSort = remember { mutableStateOf(false) }
                    val selectedSortOption = remember { mutableStateOf("Sort By") }

                    Column(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(bottom = 4.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null // Disables ripple effect
                            ) { expandedSort.value = true }
                            .border(
                                width = 1.dp,
                                shape = CircleShape,
                                color = Color.Gray.copy(alpha = 0.4f)
                            )
                            .clip(CircleShape)
                            .padding(4.dp) // Added padding for better touch interaction
                    ) {
                        Row(
                            modifier = Modifier.wrapContentSize().padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Sort By",
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = if(!expandedSort.value)painterResource(id = R.drawable.drop_down)else painterResource(id = R.drawable.drop_up),
                                contentDescription = "Sort Dropdown"
                            )
                        }

                        DropdownMenu(
                            modifier = Modifier.wrapContentWidth().padding(horizontal = 4.dp),
                            containerColor = Color.White,
                            shape = RoundedCornerShape(20.dp),
                            expanded = expandedSort.value,
                            onDismissRequest = { expandedSort.value = false }
                        ) {
                            sortOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        selectedSortOption.value = option
                                        expandedSort.value = false
                                        val sortedProducts = applySorting(option, activeProducts)
                                        productViewModel.updateProductList(sortedProducts) // Update ViewModel or State
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
                                imageUri = product.productImageUri,
                                onSlidingBackward = {}
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(110.dp))
                        }
                    }
                }
            }

            is Results.Failure -> {
                val errorMessage = state.exception.message ?: "Unknown error"
                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
            }

            null -> {}
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
