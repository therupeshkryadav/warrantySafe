package com.warrantysafe.app.presentation.ui.screens.main.utils.searchScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.model.Recent
import com.warrantysafe.app.domain.utils.Results
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.productCardList.components.ProductCard
import com.warrantysafe.app.presentation.ui.screens.main.utils.searchScreen.components.RecentItem
import com.warrantysafe.app.presentation.ui.screens.main.utils.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.viewModel.ProductViewModel
import com.warrantysafe.app.presentation.viewModel.RecentViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    navController: NavController
) {
    val recentViewModel: RecentViewModel = koinViewModel()
    val productViewModel: ProductViewModel = koinViewModel()
    val searchResults by productViewModel.searchResults.observeAsState(initial = Results.Loading)

    LaunchedEffect(Unit) {
        recentViewModel.loadRecentSearches()
    }
    var text by remember { mutableStateOf("") }
    val focusRequester = FocusRequester()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomTopAppBar(
            title = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(color = colorResource(R.color.transparent))
                ) {
                    // Placeholder
                    if (text.isEmpty()) {
                        Text(
                            text = "Search",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) {
                                    focusRequester.requestFocus()
                                }
                                .background(color = colorResource(R.color.transparent))
                        )
                    }

                    // TextField
                    BasicTextField(
                        value = text,
                        onValueChange = {
                            text = it
                          //  productViewModel.searchProducts(it) // Trigger search on text change
                        },
                        textStyle = MaterialTheme.typography.titleLarge.copy(
                            color = colorResource(R.color.black)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .background(color = colorResource(R.color.transparent))
                            .focusRequester(focusRequester),
                        singleLine = true
                    )
                }
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
                IconButton(onClick = {
                    recentViewModel.addToRecent(text) // Save query to recent list
                    productViewModel.searchProducts(text) // Trigger search
                    // Optional: Handle search icon click
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.search_warranty),
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        )

        // Initially show Recent Searches
        if (text.isEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(recentViewModel.recent.value) { recentSearch ->
                    RecentItem(Recent(recent = recentSearch.recent))
                }
            }
        }

        // Show Products only when text is not empty
        if (text.isNotEmpty()) {
            when (searchResults) {
                is Results.Loading -> {
                    Text(
                        text = "Searching...",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                is Results.Success -> {
                    val matchedList = (searchResults as Results.Success<List<Product>>).data
                    if (matchedList.isNotEmpty()) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(top = 8.dp, start = 8.dp, end = 8.dp)
                        ) {
                            items(matchedList.size) { index ->
                                val product = matchedList[index]
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
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    } else {
                        Text(
                            text = "No products found",
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
                is Results.Failure -> {
                    Text(
                        text = "Failed to load products",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}


private fun navigateToDetails(product: Product, navController: NavController) {

    val route = Route.ProductDetailsScreen.createRoute(
        id = product.id
    )
    Log.d("fatal", "Navigating to route: $route")
    navController.navigate(route)
}
