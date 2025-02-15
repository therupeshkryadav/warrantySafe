package com.warrantysafe.app.presentation.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelStoreOwner
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.warrantysafe.app.data.worker.WarrantyWorker
import com.warrantysafe.app.presentation.navigation.AppNavGraph
import com.warrantysafe.app.presentation.ui.theme.WarrantySafeTheme
import com.warrantysafe.app.utils.NotificationHelper
import com.warrantysafe.app.utils.WarrantyNotificationScheduler

class MainActivity : ComponentActivity(), ViewModelStoreOwner {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {

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
