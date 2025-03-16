package com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.productCardList

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.utils.Results
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.productCardList.components.ProductCard
import com.warrantysafe.app.presentation.ui.screens.main.utils.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.viewModel.ProductViewModel
import com.warrantysafe.app.utils.checkValidNetworkConnection
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductCardList(
    navController: NavController
) {
    val context = LocalContext.current
    val productViewModel: ProductViewModel = koinViewModel()
    // Observe the result state
    val productState by productViewModel.allProductsState.observeAsState(initial = Results.Loading)
    val isConnected = remember { mutableStateOf(checkValidNetworkConnection(context)) }
    LaunchedEffect(Unit) {
        productViewModel.loadAllProducts()
    }

    val sortOptions = listOf(
        "Old to Recent",
        "Recent to Old"
    )
    val expandedSort = remember { mutableStateOf(false) }
    val selectedSortOption = remember { mutableStateOf("Sort By") }
    // State to keep track of selected products
    val selectedProducts = remember { mutableStateListOf<Product>() }

    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopAppBar(
            title = {
                Text(
                    text = "List of Product Cards",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,  // Handling overflow text
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            actions = {}
        )
        when (productState) {
            is Results.Loading -> {
                // Loading state UI
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is Results.Success -> {
                // Display products when the state is success
                val allProducts = (productState as Results.Success).data
                if (allProducts.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // First Box (Sort By Section)
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
                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(horizontal = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Sort By",
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    painter = if (!expandedSort.value) painterResource(id = R.drawable.drop_down) else painterResource(
                                        id = R.drawable.drop_up
                                    ),
                                    contentDescription = "Sort Dropdown"
                                )
                            }

                            DropdownMenu(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .padding(horizontal = 4.dp),
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
                                            val sortedProducts = applySorting(option, allProducts)
                                            productViewModel.updateProductList(sortedProducts) // Update ViewModel or State
                                        }
                                    )
                                }
                            }
                        }
                        if (selectedProducts.isNotEmpty()) {
                            Icon(
                                modifier = Modifier.clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null // Disables ripple effect
                                ) {
                                    isConnected.value = checkValidNetworkConnection(context)
                                    if (!isConnected.value) {
                                        Toast.makeText(
                                            context,
                                            "Delete not Possible, No Valid Internet Connection!!",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    } else {
                                        productViewModel.deleteProducts(selectedProducts)
                                    }

                                },
                                imageVector = Icons.Default.Delete,
                                contentDescription = null
                            )
                        }

                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 16.dp)
                            .padding(horizontal = 8.dp)
                    ) {
                        items(allProducts) { product ->
                            val isSelected = selectedProducts.contains(product)

                            ProductCard(
                                productName = product.productName,
                                purchase = product.purchase,
                                expiry = product.expiry,
                                category = product.category,
                                imageUri = product.productImageUri,
                                itemTint = Color.Transparent,
                                detailsColor = Color.Black,
                                isSelected = isSelected,
                                showSelection = true, // ✅ Show selection box only if selected
                                onSelectToggle = {
                                    if (isSelected) {
                                        selectedProducts.remove(product)
                                    } else {
                                        selectedProducts.add(product)
                                    }
                                },
                                onClick = { navigateToDetails(product, navController) }
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }

            is Results.Failure -> {
                // Error state UI
                val errorMessage = (productState as Results.Failure).exception
                Log.d("Error", "error why failure occurred? $errorMessage")

                // Empty state if no products are available
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),
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
                        text = errorMessage.localizedMessage,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 16.dp)
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
    ) // Placeholder for expiry logic
    Log.d("fatal", "Navigating to route: $route")
    navController.navigate(route)
}