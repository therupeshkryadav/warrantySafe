package com.warrantysafe.app.presentation.ui.screens.common.customTopAppBar.components

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.navigation.Route

@SuppressLint("ComposableNaming")
@Composable
fun navigationIcons(
    navController: NavHostController,
    currentRoute: String
) {
    val navigationIcon = when (currentRoute) {
        Route.ProfileScreen.route -> {
            val coroutineScope = rememberCoroutineScope()
            NavigationIconConfig(
                icon = Icons.Filled.Menu,
                contentDescription = "Menu",
                onClick = {  }
            )
        }

        Route.AddScreen.route -> {
            NavigationIconConfig(
                icon = Icons.Filled.Close,
                contentDescription = "Close",
                onClick = { navController.popBackStack() }
            )
        }

        Route.SearchScreen.route, Route.HelpSupportScreen.route, Route.TermsPrivacyScreen.route, Route.AboutAppScreen.route, Route.UpcomingFeaturesScreen.route, Route.ProductCardList.route, Route.NotificationScreen.route, Route.ProductDetailsScreen.route, Route.EditProfileScreen.route, Route.EditProductDetailsScreen.route, Route.SettingsScreen.route -> {
            NavigationIconConfig(
                iconPainter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "Back",
                onClick = { navController.popBackStack() }
            )
        }

        else -> null // No icon for unrecognized or fallback routes
    }

    navigationIcon?.let {
        IconButton(onClick = it.onClick) {
            if (it.icon != null) {
                Icon(imageVector = it.icon, contentDescription = it.contentDescription)
            } else if (it.iconPainter != null) {
                Icon(
                    painter = it.iconPainter,
                    contentDescription = it.contentDescription,
                    tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

data class NavigationIconConfig(
    val icon: androidx.compose.ui.graphics.vector.ImageVector? = null,
    val iconPainter: androidx.compose.ui.graphics.painter.Painter? = null,
    val contentDescription: String,
    val onClick: () -> Unit
)

@Preview(showBackground = true)
@Composable
fun NavigationIconsPreview() {
    navigationIcons(
        navController = rememberNavController(),
        currentRoute = Route.HomeScreen.route
    )
}
