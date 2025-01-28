package com.warrantysafe.app.presentation.ui.screens.main.homeScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.utils.Results
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.main.homeScreen.tabs.ActiveTab
import com.warrantysafe.app.presentation.ui.screens.main.homeScreen.tabs.ExpiredTab
import com.warrantysafe.app.presentation.ui.screens.main.utils.customBottomNavigation.CustomBottomNavigation
import com.warrantysafe.app.presentation.ui.screens.main.utils.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.ui.screens.main.utils.dropDownMenu.DropDownMenuContent
import com.warrantysafe.app.presentation.ui.screens.main.utils.sideDrawer.SideDrawerContent
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
    val context = LocalContext.current

    LaunchedEffect(Unit){
        userViewModel.getUser()
    }

    // States to handle loading and errors
    val userState = userViewModel.userState.observeAsState()
    var username by remember { mutableStateOf("") }
    // Handle user state
    when (val result = userState.value) {
        is Results.Loading -> {
            username= "----"
        }
        is Results.Success -> {
            username = (result.data).username
        }
        is Results.Failure -> {
            val errorMessage = result.exception.message?: "Unknown error"
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }
        else -> {
            // No-op for null or undefined states
        }
    }

    val signOutState = userViewModel.signOutState.observeAsState()
    when (val result = signOutState.value) {
        is Results.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is Results.Success -> {
            // Navigate to LoginScreen or show a success message
            navController.navigate(Route.LoginScreen.route) {
                popUpTo(Route.HomeScreen.route) { inclusive = true }
            }
        }
        is Results.Failure -> {
            val errorMessage = result.exception.message ?: "Sign out failed due to an unknown error."
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = errorMessage, color = Color.Red, textAlign = TextAlign.Center)
            }
        }
        else -> {
            // No-op for null or undefined states
        }
    }

    // State to manage the visibility of the dropdown menu
    var isMenuExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding()
            .statusBarsPadding()
    ) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentWidth()
                ) {
                    val maxHeight = maxHeight

                    // Here we calculate the available height and adjust content dynamically
                    SideDrawerContent(
                        modifier = Modifier
                            .fillMaxHeight(),// Fills the available height
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
                }
            },
            gesturesEnabled = true
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .systemBarsPadding()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    // Custom Top App Bar
                    CustomTopAppBar(
                        title = {
                            Text(
                                text = "Welcome, $username !!",
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
                                    onItemClicked = {
                                        userViewModel.signOutUser()
                                    }
                                )
                            }
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
                        modifier = Modifier.fillMaxSize()
                    ) { page ->
                        when (page) {
                            0 ->
                                ActiveTab(navController = navController)
                            1 ->
                                ExpiredTab(navController = navController)
                        }
                    }
                }

                // Bottom Navigation fixed at the bottom
                CustomBottomNavigation(
                    currentRoute = Route.HomeScreen,
                    onItemClick = { route -> navigateToTab(navController, route) },
                    modifier = Modifier.align(Alignment.BottomCenter).wrapContentHeight() // Fix at the bottom of the screen
                )
            }
        }
    }
}


fun navigateToTab(navController: NavController, route: Route) {
    navController.navigate(route.route) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true // Save state for tabs
        }
        launchSingleTop = true // Avoid multiple instances of the same destination
        restoreState = true // Restore the state if previously saved
    }
}
