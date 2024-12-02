package com.warrantysafe.app.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.common.SearchBar
import com.warrantysafe.app.presentation.navgraph.Route
import com.warrantysafe.app.presentation.product.ProductCard
import com.warrantysafe.app.presentation.search.SearchScreen

@Composable
fun HomeScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp,bottom = 8.dp)
                .padding(horizontal = 32.dp)
                .clip(shape = RoundedCornerShape(28.dp))
                .clickable {
                    navController.navigate(route = Route.SearchScreen.route)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar(
                query = "",
                enabled = false,
                onQueryChange = {},
                onSearch = {}
            )
        }
        ProductCard(
            title = "Realme 3 Pro",
            purchase = "30/11/2024",
            description = "1 years 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.warranty_logo // Replace with your drawable resource
        )
        ProductCard(
            title = "Whirpool 5-star inverter AC",
            purchase = "30/11/2024",
            description = "5 years 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.warranty_logo // Replace with your drawable resource
        )
        ProductCard(
            title = "TVS raider 125cc",
            purchase = "30/11/2024",
            description = "1 years 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.warranty_logo // Replace with your drawable resource
        )

        ProductCard(
            title = "Whirpool 5-star inverter AC",
            purchase = "30/11/2024",
            description = "5 years 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.warranty_logo // Replace with your drawable resource
        )
        ProductCard(
            title = "TVS raider 125cc",
            purchase = "30/11/2024",
            description = "1 years 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.warranty_logo // Replace with your drawable resource
        )

        Spacer(modifier = Modifier.height(24.dp))

    }
}

@Composable
fun SearchNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = Route.SearchScreen.route) {
        composable(Route.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val homeNavController = rememberNavController()
    HomeScreen(
        homeNavController
    )
}