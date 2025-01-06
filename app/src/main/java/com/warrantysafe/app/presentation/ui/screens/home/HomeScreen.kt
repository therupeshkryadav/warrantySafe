package com.warrantysafe.app.presentation.ui.screens.home

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.common.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.ui.screens.common.dropDownMenu.DropDownMenuContent
import com.warrantysafe.app.presentation.ui.screens.common.sideDrawer.SideDrawerContent
import com.warrantysafe.app.presentation.ui.screens.home.components.tabs.ActiveTab
import com.warrantysafe.app.presentation.ui.screens.home.components.tabs.ExpiredTab
import com.warrantysafe.app.presentation.ui.screens.warranty_navigator.components.BottomNavigationItem
import com.warrantysafe.app.presentation.ui.screens.warranty_navigator.components.WarrantyBottomNavigation
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    activeProducts: List<Product>,
    expiredProducts: List<Product>,
    navController: NavController,
    user: User
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    // State to manage the visibility of the dropdown menu
    var isMenuExpanded by remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideDrawerContent(
                onItemClicked = { item ->
                    coroutineScope.launch { drawerState.close() }
                    when (item) {
                        "List of Product Cards" -> navigateToTab(navController, Route.ProductCardList)
                        "Help & Support" -> navigateToTab(navController, Route.HelpSupportScreen)
                        "Terms & Privacy" -> navigateToTab(navController, Route.TermsPrivacyScreen)
                        "About the App" -> navigateToTab(navController, Route.AboutAppScreen)
                        "Upcoming Features" -> navigateToTab(navController, Route.UpcomingFeaturesScreen)
                        "Settings" -> navigateToTab(navController, Route.SettingsScreen)
                    }
                }
            )
        },
        gesturesEnabled = true
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // Custom Top App Bar
                CustomTopAppBar(
                    title = {
                        Text(
                            text = "Welcome, ${user.userName}!!",
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { coroutineScope.launch { drawerState.open() } }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { navController.navigate(route = Route.NotificationScreen.route) }) {
                            Icon(
                                imageVector = Icons.Filled.Notifications,
                                contentDescription = "Notifications"
                            )
                        }
                        IconButton(onClick = {isMenuExpanded = !isMenuExpanded}) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "More Options"
                            )
                        }
                        // Dropdown Menu
                        DropdownMenu(
                            expanded = isMenuExpanded,
                            containerColor = Color.LightGray,
                            onDismissRequest = { isMenuExpanded = false }
                        ) {
                            DropDownMenuContent(
                                navController = navController,
                                dropDownList = listOf("Logout"),
                                onItemClicked = {}
                            )
                        }
                    }
                )

                // Main Content
                val tabTitles = listOf("Active", "Expired")
                val pagerState = rememberPagerState(initialPage = 0, pageCount = { tabTitles.size })
                val scope = rememberCoroutineScope()

                // TabRow and Pager
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    containerColor = colorResource(R.color.transparent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    tabTitles.forEachIndexed { index, title ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.Transparent)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null // Disables ripple effect
                                ) {
                                    scope.launch { pagerState.animateScrollToPage(index) }
                                }
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
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

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    when (page) {
                        0 ->
                            ActiveTab(navController = navController, activeProducts = activeProducts)
                        1 ->
                            ExpiredTab(navController = navController, expiredProducts = expiredProducts)
                    }
                }
            }

            // Bottom Navigation fixed at the bottom
            WarrantyBottomNavigation(
                items = listOf(
                    BottomNavigationItem(
                        icon = R.drawable.home_warranty,
                        text = "Home",
                        route = Route.HomeScreen
                    ),
                    BottomNavigationItem(
                        icon = R.drawable.add_warranty,
                        text = "Add",
                        route = Route.AddScreen
                    ),
                    BottomNavigationItem(
                        icon = R.drawable.profile_warranty,
                        text = "Profile",
                        route = Route.ProfileScreen
                    )
                ),
                currentRoute = Route.HomeScreen,
                onItemClick = { route -> navigateToTab(navController, route) },
                modifier = Modifier.align(Alignment.BottomCenter) // Fix at the bottom of the screen
            )
        }
    }
}


private fun navigateToTab(navController: NavController, route: Route) {
    navController.navigate(route.route) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true // Save state for tabs
        }
        launchSingleTop = true // Avoid multiple instances of the same destination
        restoreState = true // Restore the state if previously saved
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        activeProducts = listOf(
            Product(
                title = "Realme 3 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Realme 7 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Redmi Note 10 ",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Realme 3 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Realme 7 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Redmi Note 10 ",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Realme 3 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Realme 7 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Redmi Note 10 ",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Realme 3 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Realme 7 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Redmi Note 10 ",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Redmi Note 10 ",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Realme 3 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Realme 7 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Redmi Note 10 ",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Redmi Note 10 ",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Realme 3 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Realme 7 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Redmi Note 10 ",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            )

        ),
        expiredProducts = listOf(
            Product(
                title = "Rado Watch",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "PS5",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "LG Washing Machine ",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Rado Watch",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "PS5",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "LG Washing Machine ",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Rado Watch",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "PS5",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "LG Washing Machine ",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "Rado Watch",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "PS5",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product(
                title = "LG Washing Machine ",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            )
        ),
        navController = rememberNavController(),
        user = User(
            "Rupesh Kumar Yadav",
            "therupeshkryadav",
            "rupesh.official484@gmail.com",
            "7233966649",
        )
    )
}