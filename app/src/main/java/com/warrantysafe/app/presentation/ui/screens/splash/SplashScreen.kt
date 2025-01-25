package com.warrantysafe.app.presentation.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.theme.WarrantySafeTheme
import com.warrantysafe.app.presentation.viewModel.UserViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    navController: NavController
) {
    val userViewModel: UserViewModel = koinViewModel()
    val navigationRoute by userViewModel.navigationRoute.observeAsState()

    // Trigger checkUser when SplashScreen is launched
    LaunchedEffect(Unit) {
        userViewModel.checkUser() // Call the ViewModel to determine the next screen
        delay(2000L) // Splash screen delay (optional)
    }

    // Navigate to the determined route when available
    LaunchedEffect(navigationRoute) {
        navigationRoute?.let { route ->
            navController.navigate(route) {
                popUpTo(Route.SplashScreen.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), // Primary color
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(painter = painterResource(R.drawable.warranty_logo),
                contentDescription = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    WarrantySafeTheme {
        SplashScreen(navController = rememberNavController()) // Preview without navigation
    }
}
