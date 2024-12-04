package com.warrantysafe.app.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.home.components.Tabs.ActiveTab
import com.warrantysafe.app.presentation.home.components.Tabs.ExpiredTab
import com.warrantysafe.app.presentation.navgraph.Route
import kotlinx.coroutines.launch


sealed class Product {
    data class Active(
        val title: String,
        val purchase: String,
        val period: String,
        val progress: Float,
        val imageResId: Int
    ) : Product()

    data class Expired(
        val title: String,
        val purchase: String,
        val period: String,
        val progress: Float,
        val imageResId: Int
    ) : Product()
}



@Composable
fun HomeScreen(
    navController: NavController
) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    val activeProducts = listOf(
        Product.Active(
            title = "Realme 3 Pro",
            purchase = "30/11/2024",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Realme 7 Pro",
            purchase = "30/11/2024",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Redmi Note 10 ",
            purchase = "30/11/2024",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        )
    )

    val expiredProducts = listOf(
        Product.Expired(
            title = "Realme 3 Pro",
            purchase = "30/11/2024",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "Realme 7 Pro",
            purchase = "30/11/2024",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "Redmi Note 10 ",
            purchase = "30/11/2024",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
    ) {

        //Search Bar
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

        val tabTitles = listOf("Active", "Expired")

        val pagerState = rememberPagerState( initialPage= 0,
            pageCount = { tabTitles.size })
        val scope = rememberCoroutineScope()

        // TabRow for displaying the tabs
        TabRow(
            selectedTabIndex = pagerState.currentPage, // Highlights the active tab
            modifier = Modifier.fillMaxWidth()
        ) {
            tabTitles.forEachIndexed { index, title ->
                // Define individual tabs
                Tab(
                    selected = pagerState.currentPage == index, // Highlight the selected tab
                    onClick = {
                        // Navigate to the clicked tab
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(text = title) }
                )
            }
        }

        // HorizontalPager for the content
        HorizontalPager(
            state = pagerState, // Synchronizes with TabRow
            modifier = Modifier.fillMaxSize()
        ) { page ->
            // Content for each page
            when (page) {
                0 -> ActiveTab(activeProducts = activeProducts) // Content for Tab 1
                1 -> ExpiredTab(expiredProducts = expiredProducts) // Content for Tab 2
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