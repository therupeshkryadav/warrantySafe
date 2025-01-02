package com.warrantysafe.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.presentation.ui.screens.loginSignUpScreen.LoginSignUpScreen
import com.warrantysafe.app.presentation.ui.screens.splashSheet.SplashSheet
import com.warrantysafe.app.presentation.ui.screens.warranty_navigator.WarrantyNavigator

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    // App Start Flow (Splash to BottomNav)
    NavHost(navController, startDestination = Route.SplashSheet.route) {

        // SplashScreen
        composable(Route.SplashSheet.route) {
            SplashSheet(
                navController = navController,
                onFinish = {
                    navController.navigate(Route.LoginSignUpScreen.route) {
                        popUpTo(Route.SplashSheet.route) { inclusive = true }
                    }
                }
            )
        }

        // LoginSignUpScreen
        composable(Route.LoginSignUpScreen.route) {
            LoginSignUpScreen(
                onLoginSuccess = {
                    navController.navigate(Route.WarrantyNavigator.route) {
                        popUpTo(Route.LoginSignUpScreen.route) { inclusive = true }
                    }
                },
                onSignUpSuccess = {
                    navController.navigate(Route.LoginSignUpScreen.route)
                }
            )
        }

        // WarrantyNavigator Flow
        composable(Route.WarrantyNavigator.route) {
            val bottomNavController = rememberNavController()
            WarrantyNavigator(
                navController = bottomNavController,
                startDestination = Route.HomeScreen.route
            )
        }

    }
}