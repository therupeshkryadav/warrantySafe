package com.warrantysafe.app.presentation.ui.screens.searchScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.utils.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.ui.screens.utils.productCardList.components.ProductCard
import com.warrantysafe.app.presentation.ui.screens.searchScreen.components.RecentItem

@Composable
fun SearchScreen(
    navController: NavController,
    recentSearches: List<String>
) {
    var text by remember { mutableStateOf("") }
    // Content under the TopAppBar
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CustomTopAppBar(
            title = {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(1f)
                                .clickable { }
                                .background(color = colorResource(R.color.transparent)),
                            text = "Search",
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(62.dp)
                        .background(color = colorResource(R.color.transparent)),
                    textStyle = MaterialTheme.typography.titleLarge,
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = colorResource(R.color.black),
                        unfocusedTextColor = colorResource(R.color.black),
                        focusedContainerColor = colorResource(R.color.transparent),
                        unfocusedContainerColor = colorResource(R.color.transparent),
                        focusedIndicatorColor = Color.Transparent, // Removes the bottom line when focused
                        unfocusedIndicatorColor = Color.Transparent // Removes the bottom line when unfocused
                    )
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
            actions = {
                IconButton(onClick = { /* Handle Search Click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.search_warranty),
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        )

        val matchedList = mutableListOf<Product>()

        if (matchedList.isEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(recentSearches) { recentSearch ->
                    RecentItem(recent = recentSearch)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 8.dp, start = 8.dp, end = 8.dp)
            ) {
                // Display Products
                items(matchedList.size) { index ->
                    val product = matchedList[index]
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
    }
}

private fun navigateToDetails(product: Product, navController: NavController) {

    val route = Route.ProductDetailsScreen.createRoute(
        productName = product.title,  // Correct property name
        purchaseDate = product.purchase,
        category = product.category,
        expiryDate = product.expiry, // Placeholder for expiry logic
    )
    Log.d("fatal", "Navigating to route: $route")
    navController.navigate(route)
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchScreen() {
    SearchScreen(
        rememberNavController(),
        recentSearches = listOf("recent1", "recent2", "recent3", "recent4")
    )
}
