package com.warrantysafe.app.presentation.ui.screens.main.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.main.homeScreen.tabs.ActiveTab
import com.warrantysafe.app.presentation.ui.screens.main.homeScreen.tabs.ExpiredTab
import com.warrantysafe.app.presentation.ui.screens.main.utils.customBottomNavigation.CustomBottomNavigation
import com.warrantysafe.app.presentation.ui.screens.main.utils.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.ui.screens.main.utils.dropDownMenu.DropDownMenuContent
import com.warrantysafe.app.presentation.ui.screens.main.utils.sideDrawer.SideDrawerContent
import com.warrantysafe.app.presentation.viewModel.ProductViewModel
import com.warrantysafe.app.presentation.viewModel.UserViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val userViewModel: UserViewModel = koinViewModel()
    val productViewModel: ProductViewModel = koinViewModel()


    LaunchedEffect(Unit){
        productViewModel.loadActiveProducts()
        productViewModel.loadExpiredProducts()
    }
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
                            text = "Welcome, username!!",
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
                                onItemClicked = {  }
                            )
                        }
//                        // Observe the sign-out result to trigger navigation
//                        LaunchedEffect(signOutState) {
//                            if (signOutState?.isSuccess == true) {
//                                // After sign-out, navigate to LoginSignUpScreen
//                                navController.popBackStack(Route.HomeScreen.route, inclusive = true)
//                                navController.navigate(Route.LoginSignUpScreen.route)
//                            } else if (signOutState?.isFailure == true) {
//                                // Handle failure if needed
//                                Toast.makeText(context, "Sign out failed", Toast.LENGTH_SHORT).show()
//                            }
//                        }
                    }
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(50))
                            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(50) )
                            .background(Color.White) // Soft background for better aesthetics
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null // Disables ripple effect
                            ) {
                                navController.navigate(route = Route.SearchScreen.route)
                            }
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.search_warranty),
                                contentDescription = null,
                                tint = Color.DarkGray, // Subtle color for the icon
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp)) // Space between icon and text
                            Text(
                                text = "Search ",
                                fontSize = 18.sp,
                                color = Color.DarkGray, // Subtle text color
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }


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
                    modifier = Modifier.fillMaxHeight(0.84f)
                ) { page ->
                    when (page) {
                        0 ->
                            ActiveTab(navController = navController, activeProducts = productViewModel.activeProducts.value)
                        1 ->
                            ExpiredTab(navController = navController, expiredProducts = productViewModel.expiredProducts.value)
                    }
                }
            }

            // Bottom Navigation fixed at the bottom
            CustomBottomNavigation(
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
