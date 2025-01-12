package com.warrantysafe.app.presentation.ui.screens.profileScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
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
import androidx.compose.ui.layout.ContentScale
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
import com.warrantysafe.app.presentation.ui.screens.profileScreen.components.DetailRow
import com.warrantysafe.app.presentation.ui.screens.utils.customBottomNavigation.CustomBottomNavigation
import com.warrantysafe.app.presentation.ui.screens.utils.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.ui.screens.utils.dropDownMenu.DropDownMenuContent
import com.warrantysafe.app.presentation.ui.screens.utils.sideDrawer.SideDrawerContent
import com.warrantysafe.app.presentation.viewModel.UserViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    navController: NavController
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    // State to manage the visibility of the dropdown menu
    val userViewModel: UserViewModel = koinViewModel()
    val user = userViewModel.user.value
    LaunchedEffect(Unit) {
        userViewModel.loadUserDetails()
    }
    var isMenuExpanded by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideDrawerContent(
                onItemClicked = { item ->
                    coroutineScope.launch { drawerState.close() }
                    // Perform navigation or actions based on the clicked item
                    when (item) {
                        "List of Product Cards" -> {
                            navigateToTab(
                                navController = navController,
                                route = Route.ProductCardList
                            ) // Handle List of Product navigation
                        }

                        "Help & Support" -> {
                            navigateToTab(
                                navController = navController,
                                route = Route.HelpSupportScreen
                            ) // Handle Help & Support navigation
                        }

                        "Rate and Review" -> {
                            // Handle Rate and Review navigation
                        }

                        "Share with Friends" -> {
                            // Handle Share with Friends navigation
                        }

                        "Terms & Privacy" -> {
                            navigateToTab(
                                navController = navController,
                                route = Route.TermsPrivacyScreen
                            )// Handle Terms & Privacy navigation
                        }

                        "About the App" -> {
                            navigateToTab(
                                navController = navController,
                                route = Route.AboutAppScreen
                            )// Handle About the App navigation
                        }

                        "Upcoming Features" -> {
                            navigateToTab(
                                navController = navController,
                                route = Route.UpcomingFeaturesScreen
                            )// Handle Upcoming Features navigation
                        }

                        "Settings" -> {
                            navigateToTab(
                                navController = navController,
                                route = Route.SettingsScreen
                            )//  Handle Settings navigation
                        }
                    }
                }
            )
        },
        gesturesEnabled = true
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                CustomTopAppBar(
                    title = {
                        Text(
                            text = " ",
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Start,
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
                        IconButton(onClick = { isMenuExpanded = !isMenuExpanded }) {
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
                
                Column(
                    modifier = Modifier
                        .fillMaxHeight(1f)
                        .padding(horizontal = 16.dp)
                        .verticalScroll(scrollState)
                ) {

                    Spacer(modifier = Modifier.size(16.dp))
                    // Profile Avatar
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .background(color = colorResource(R.color.black))
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.profile_placeholder),
                            modifier = Modifier
                                .size(198.dp)
                                .align(Alignment.Center)
                                .clip(CircleShape),
                            contentDescription = "Profile Avatar",
                            contentScale = ContentScale.Crop
                        )
                    }

                    // Profile Details
                    DetailRow(
                        "Name",
                        updatedValue = user.fullName,
                        enable = false,
                        textColor = colorResource(R.color.purple_500),
                        icon = null,
                        onValueChange = { }
                    )
                    DetailRow(
                        "Username",
                        updatedValue = user.userName,
                        enable = false,
                        textColor = colorResource(R.color.purple_500),
                        icon = null,
                        onValueChange = { }
                    )
                    DetailRow(
                        "Email",
                        updatedValue = user.emailId,
                        enable = false,
                        textColor = colorResource(R.color.purple_500),
                        icon = null,
                        onValueChange = { }
                    )
                    DetailRow(
                        "Phone",
                        updatedValue = user.phone,
                        enable = false,
                        textColor = colorResource(R.color.purple_500),
                        icon = null,
                        onValueChange = { }
                    )

                    //Edit Profile Button
                    Button(
                        onClick = {
                            navigateToEditProfile(
                            navController = navController,
                            user = user
                        )},
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            text = "Edit Profile",
                            fontSize = 18.sp,
                            color = colorResource(R.color.white)
                        )
                        Icon(
                            modifier = Modifier
                                .width(18.dp)
                                .padding(start = 4.dp)
                                .fillMaxHeight(),
                            imageVector = Icons.Filled.Edit,
                            tint = colorResource(R.color.white),
                            contentDescription = "Edit Profile"
                        )
                    }

                    Spacer(modifier = Modifier.size(120.dp))
                }
            }
            // Bottom Navigation fixed at the bottom
            CustomBottomNavigation(
                currentRoute = Route.ProfileScreen,
                onItemClick = { route -> navigateToTab(navController, route) },
                modifier = Modifier.align(Alignment.BottomCenter) // Fix at the bottom of the screen
            )
        }


    }
}

fun navigateToEditProfile(
    navController: NavController,
    user: User
) {
    val route = Route.EditProfileScreen.createRoute(
        fullName = user.fullName,
        userName = user.userName,
        emailId = user.emailId,
        phone = user.phone
    )
    navController.navigate(route)
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