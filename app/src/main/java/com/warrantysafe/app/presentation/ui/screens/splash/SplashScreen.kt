package com.warrantysafe.app.presentation.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    // This box will show the logo and be centered
   // NotificationHelper.sendNotification(context,"App Started","Welcome to Warranty Safe!!")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.warranty_logo),
                contentDescription = "Warranty Safe Logo"
            )
            Text(
                text = "v1.0.0",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    // Use ViewModel to manage navigation logic
    val userViewModel: UserViewModel = koinViewModel()
    val navigationRoute by userViewModel.navigationRoute.observeAsState()

    // Handle navigation logic with delay
    LaunchedEffect(Unit) {
        delay(2000L) // Show splash screen for 2 seconds

        // Call checkUser after the delay to decide the navigation route
        userViewModel.checkUser()

        // After checkUser logic, navigate if route is available
        navigationRoute?.let { route ->
            navController.navigate(route) {
                // Pop up to splash screen to avoid back navigation to it
                popUpTo(Route.SplashScreen.route) { inclusive = true }
            }
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
