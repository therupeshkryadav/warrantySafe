package com.warrantysafe.app.presentation.common.customTopAppBar.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.common.dropDownMenu.DropDownMenuContent
import com.warrantysafe.app.presentation.navgraph.Route
import com.warrantysafe.app.presentation.warranty_navigator.WarrantyNavigator

@SuppressLint("ComposableNaming")
@Composable
fun actionIcons(
    navController: NavHostController,
    currentRoute: String
) {
    // State to manage the visibility of the dropdown menu
    var isMenuExpanded by remember { mutableStateOf(false) }

    Row {
        when (currentRoute) {
            Route.HomeScreen.route, Route.ProfileScreen.route -> {
                // Home or Profile screen actions
                IconButton(onClick = {
                    navController.navigate(route = Route.NotificationScreen.route)
                }) {
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
                    containerColor = MaterialTheme.colorScheme.surface,
                    onDismissRequest = { isMenuExpanded = false }
                ) {
                    DropDownMenuContent(
                        onItemClicked = {}
                    )
                }

            }

            Route.AddScreen.route -> {
                // Add Warranty screen action
                IconButton(onClick = {
                    // Clear back stack of Route.AddScreen.route
                    navController.popBackStack(Route.AddScreen.route, inclusive = true)

                    // Navigate to HomeScreen
                    navController.navigate(Route.HomeScreen.route)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Check Icon"
                    )
                }
            }

            Route.SearchScreen.route -> {
                // Search screen action
                IconButton(onClick = { /* Handle Search Click */ }) {
                    Icon(
                        painter = androidx.compose.ui.res.painterResource(id = R.drawable.search_warranty),
                        contentDescription = "Search",
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            else -> {
                // Fallback case if no matching route
            }
        }
    }
}

@Preview
@Composable
fun ActionsPreview() {
    actionIcons(
        navController = rememberNavController(),
        currentRoute = Route.HomeScreen.route
    )
}
