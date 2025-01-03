package com.warrantysafe.app.presentation.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.ui.screens.home.components.tabs.ActiveTab
import com.warrantysafe.app.presentation.ui.screens.home.components.tabs.ExpiredTab
import com.warrantysafe.app.presentation.navigation.Route
import kotlinx.coroutines.launch


sealed class Product {
    data class Active(
        val title: String,
        val purchase: String,
        val expiry: String,
        val category: String,
        val imageResId: Int
    ) : Product()

    data class Expired(
        val title: String,
        val purchase: String,
        val expiry: String,
        val category: String,
        val imageResId: Int
    ) : Product()
}


@Composable
fun HomeScreen(
    navController: NavController
) {

    val activeProducts = listOf(
        Product.Active(
            title = "Realme 3 Pro",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Water Geyser",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Home Appliances",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Honda Sp 125 ",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Vehicles",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Titan Watch",
            purchase = "01/10/2024",
            expiry = "01/10/2025",
            category = "Wearables",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Realme 3 Pro",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Water Geyser",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Home Appliances",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Honda Sp 125 ",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Vehicles",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Titan Watch",
            purchase = "01/10/2024",
            expiry = "01/10/2025",
            category = "Wearables",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Realme 3 Pro",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Water Geyser",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Home Appliances",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Honda Sp 125 ",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Vehicles",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Titan Watch",
            purchase = "01/10/2024",
            expiry = "01/10/2025",
            category = "Wearables",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Realme 3 Pro",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Electronics",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Water Geyser",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Home Appliances",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Honda Sp 125 ",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Vehicles",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Titan Watch",
            purchase = "01/10/2024",
            expiry = "01/10/2025",
            category = "Wearables",
            imageResId = R.drawable.item_image_placeholder
        )

    )

    val expiredProducts = listOf(
        Product.Expired(
            title = "Rado Watch",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Wearables",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "PS5",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Gadgets & Accessories",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "LG Washing Machine ",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Home Appliances",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "Rado Watch",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Wearables",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "PS5",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Gadgets & Accessories",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "LG Washing Machine ",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Home Appliances",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "Rado Watch",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Wearables",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "PS5",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Gadgets & Accessories",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "LG Washing Machine ",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Home Appliances",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "Rado Watch",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Wearables",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "PS5",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Gadgets & Accessories",
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "LG Washing Machine ",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Home Appliances",
            imageResId = R.drawable.item_image_placeholder
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {

        //Search Bar
        Box(Modifier.fillMaxWidth().padding(horizontal = 8.dp)){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(56.dp))
                    .background(color = colorResource(R.color.black))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(1.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(56.dp))
                            .background(color = colorResource(R.color.black))
                            .clickable {
                                navController.navigate(route = Route.SearchScreen.route)
                            }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .background(color = MaterialTheme.colorScheme.surface)
                                .padding(8.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.search_warranty),
                                contentDescription = null
                            )
                            Text(
                                modifier = Modifier.padding(start = 8.dp),
                                text = "Search",
                                fontSize = 20.sp,
                                color = colorResource(R.color.xtreme)
                            )
                        }
                    }
                }

            }
        }

        val tabTitles = listOf("Active", "Expired")

        val pagerState = rememberPagerState(initialPage = 0,
            pageCount = { tabTitles.size })
        val scope = rememberCoroutineScope()

        // Custom TabRow with no click effect
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            tabTitles.forEachIndexed { index, title ->
                // Custom Tab Implementation
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(8.dp)) // Optional for styling
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null // Disables ripple effect
                        ) {
                            scope.launch { pagerState.animateScrollToPage(index) }
                        }
                        .padding(8.dp),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        // HorizontalPager for the content
        HorizontalPager(
            state = pagerState, // Synchronizes with TabRow
            modifier = Modifier.fillMaxSize()
        ) { page ->
            // Content for each page
            when (page) {
                0 -> ActiveTab(
                    navController = navController,
                    activeProducts = activeProducts
                ) // Content for Tab 1
                1 -> ExpiredTab(
                    navController = navController,
                    expiredProducts = expiredProducts
                ) // Content for Tab 2
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val homeNavController = rememberNavController()
    HomeScreen(
        homeNavController
    )
}