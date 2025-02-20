package com.warrantysafe.app.presentation.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelStoreOwner
import com.warrantysafe.app.presentation.navigation.AppNavGraph
import com.warrantysafe.app.presentation.ui.theme.WarrantySafeTheme
import com.warrantysafe.app.utils.NotificationHelper
import com.warrantysafe.app.utils.WarrantyNotificationScheduler

class MainActivity : ComponentActivity(), ViewModelStoreOwner {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        // ✅ Show splash screen before anything else
        installSplashScreen()
        super.onCreate(savedInstanceState)

        // Create the notification channel
        NotificationHelper.createNotificationChannel(this)


        WarrantyNotificationScheduler.schedule(this)


        setContent {
            WarrantySafeTheme {
                AppNavGraph()
            }
        }
    }
}

