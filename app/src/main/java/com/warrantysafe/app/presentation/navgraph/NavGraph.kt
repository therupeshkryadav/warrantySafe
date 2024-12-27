package com.warrantysafe.app.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.presentation.loginSignUpScreen.LoginSignUpScreen
import com.warrantysafe.app.presentation.splashSheet.SplashSheet
import com.warrantysafe.app.presentation.warranty_navigator.WarrantyNavigator

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
                    navController.navigate(Route.BottomNavigation.route) {
                        popUpTo(Route.LoginSignUpScreen.route) { inclusive = true }
                    }
                },
                onSignUpSuccess = {
                    navController.navigate(Route.LoginSignUpScreen.route)
                }
            )
        }
        // BottomNavigation Flow
        composable(Route.BottomNavigation.route) {
            val bottomNavController = rememberNavController()
            WarrantyNavigator(
                navController = bottomNavController,
                startDestination = Route.HomeScreen.route
            )
        }

    }
}