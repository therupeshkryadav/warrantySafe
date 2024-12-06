package com.warrantysafe.app.presentation.search.components.searchList

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.common.productList.components.productCard.ProductCard
import com.warrantysafe.app.presentation.home.Product
import com.warrantysafe.app.presentation.navgraph.Route
import com.warrantysafe.app.presentation.search.components.searchList.components.recentSearches.Recent
import com.warrantysafe.app.presentation.search.components.searchList.components.recentSearches.RecentSearches

@Composable
fun SearchList(
    navController: NavController,
    matchedList: List<Product>
) {

    if (matchedList.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = colorResource(R.color.xtreme))
                .border(width = 1.dp, color = colorResource(R.color.black))
        ) {
            val recentList = listOf(
                Recent("recentSearch1"),
                Recent("recentSearch2"),
                Recent("recentSearch3"),
                Recent("recentSearch4")
            )
            Column {
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                    text = "Recent searches",
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.DaysLeft)
                )
                RecentSearches(recentSearches = recentList)
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 8.dp, start = 8.dp, end = 8.dp)
        ) {
            // Dynamically filter the input list for Active and Expired products
            val activeList = matchedList.filterIsInstance<Product.Active>()
            val expiredList = matchedList.filterIsInstance<Product.Expired>()

            // Display Active Products
            items(activeList.size) { index ->
                val product = activeList[index]
                ProductCard(
                    title = product.title,
                    purchase = product.purchase,
                    expiry = product.expiry,
                    period = product.period,
                    progress = product.progress,
                    imageResId = product.imageResId,
                    progressTint = colorResource(R.color.DaysLeft),
                    itemTint = colorResource(R.color.transparent),
                    detailsColor = MaterialTheme.colorScheme.onSurface,
                    onClick = { navigateToDetails(product, navController) }
                )
            }

            // Display Expired Products
            items(expiredList.size) { index ->
                val product = expiredList[index]
                ProductCard(
                    title = product.title,
                    purchase = product.purchase,
                    expiry = product.expiry,
                    period = product.period,
                    progress = product.progress,
                    imageResId = product.imageResId,
                    progressTint = colorResource(R.color.noDaysLeft),
                    itemTint = colorResource(R.color.expired),
                    detailsColor = MaterialTheme.colorScheme.inversePrimary,
                    onClick = { navigateToDetails(product, navController) }
                )
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
private fun PreviewSearchList() {
    SearchList(rememberNavController(), emptyList())
}
