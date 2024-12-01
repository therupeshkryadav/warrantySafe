package com.warrantysafe.app.presentation.splash

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.ui.theme.WarrantySafeTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    onFinish: () -> Unit
) {
    // Delay to show the splash screen for a few seconds
    var isSplashVisible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000L) // 2-second delay
        onFinish()   // Navigate to the next screen after the delay
    }

    // Splash Screen UI
    if (isSplashVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF6200EE)), // Use your primary color
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Welcome to WarrantySafe",
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp),
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashSheetPreview() {
    WarrantySafeTheme {
        SplashScreen(navController = rememberNavController(),
            onFinish = {})
    }
}
