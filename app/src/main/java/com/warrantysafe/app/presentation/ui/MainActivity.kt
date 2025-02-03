package com.warrantysafe.app.presentation.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelStoreOwner
import com.warrantysafe.app.presentation.navigation.AppNavGraph
import com.warrantysafe.app.presentation.ui.theme.WarrantySafeTheme
import com.warrantysafe.app.utils.NotificationHelper

class MainActivity : ComponentActivity(), ViewModelStoreOwner {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // Create the notification channel
        NotificationHelper.createNotificationChannel(this)


        setContent {
            WarrantySafeTheme {
                AppNavGraph()
            }
        }
    }
}
