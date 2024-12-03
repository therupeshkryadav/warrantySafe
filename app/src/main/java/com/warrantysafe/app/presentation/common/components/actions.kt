package com.warrantysafe.app.presentation.common.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.navgraph.Route

@Composable
fun actions(
    navController: NavHostController,
    isSearch: Boolean,
    isAddWarranty: Boolean,
    isHomeorProfile: Boolean
) {
    Row {
        if (isHomeorProfile.equals(true)) {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Overflow Menu Icon"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Overflow Menu Icon"
                )
            }
        } else if (isAddWarranty.equals(true)) {
            IconButton(onClick = {
                // Clear back stack of Route.AppScreen.route
                navController.popBackStack(Route.AddScreen.route, inclusive = true)

                // Navigate to HomeScreen
                navController.navigate(Route.HomeScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Check Icon"
                )
            }
        } else if (isSearch.equals(true)) {
            IconButton(onClick = {}) {
                Icon(
                    painter = androidx.compose.ui.res.painterResource(id = R.drawable.search_warranty),
                    contentDescription = "Search",
                    tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Preview
@Composable
fun actionsPreview() {
    actions(
        navController = rememberNavController(),
        isSearch = true,
        isAddWarranty = false,
        isHomeorProfile = false
    )
}