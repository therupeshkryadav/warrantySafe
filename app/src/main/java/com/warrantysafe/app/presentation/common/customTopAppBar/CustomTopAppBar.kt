package com.warrantysafe.app.presentation.common.customTopAppBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.common.customTopAppBar.components.actionIcons
import com.warrantysafe.app.presentation.common.customTopAppBar.components.navigationIcons
import com.warrantysafe.app.presentation.common.customTopAppBar.components.titleAppBar
import com.warrantysafe.app.presentation.navgraph.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    navController: androidx.navigation.NavHostController,
    currentRoute: String,
    drawerState: DrawerState
) {


    Column(modifier = Modifier.fillMaxWidth()) {
        CenterAlignedTopAppBar(
            title = {
                titleAppBar(currentRoute = currentRoute)
            },
            navigationIcon = {
                navigationIcons(
                    navController = navController,
                    currentRoute = currentRoute,
                    drawerState = drawerState
                )
            },
            actions = {
                actionIcons(
                    navController = navController,
                    currentRoute = currentRoute
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
                titleContentColor = colorResource(R.color.black)
            )
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = colorResource(R.color.xtreme))
        )
    }
}

@Preview(showBackground = true, name = "SmartCustomTopAppBar Previews")
@Composable
fun SmartCustomTopAppBarPreview() {
    val navController = rememberNavController()

    Column {
        // Home Screen Preview
        CustomTopAppBar(
            navController = navController,
            currentRoute = Route.HomeScreen.route,
            drawerState = rememberDrawerState(DrawerValue.Closed)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Profile Screen Preview
        CustomTopAppBar(
            navController = navController,
            currentRoute = Route.ProfileScreen.route,
            drawerState = rememberDrawerState(DrawerValue.Closed)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Add Warranty Screen Preview
        CustomTopAppBar(
            navController = navController,
            currentRoute = Route.AddScreen.route,
            drawerState = rememberDrawerState(DrawerValue.Closed)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Search Screen Preview
        CustomTopAppBar(
            navController = navController,
            currentRoute = Route.SearchScreen.route,
            drawerState = rememberDrawerState(DrawerValue.Closed)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Unknown Route Preview
        CustomTopAppBar(
            navController = navController,
            currentRoute = "unknown_route",
            drawerState = rememberDrawerState(DrawerValue.Closed)
        )
    }
}
