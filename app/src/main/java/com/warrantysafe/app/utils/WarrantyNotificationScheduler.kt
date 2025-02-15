package com.warrantysafe.app.utils

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.warrantysafe.app.data.worker.WarrantyWorker
import java.util.concurrent.TimeUnit

object WarrantyNotificationScheduler {
    fun schedule(context: Context) {
        // Schedule the worker to run every 15 minutes
        val request = PeriodicWorkRequestBuilder<WarrantyWorker>(15, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "warranty_notification",
            ExistingPeriodicWorkPolicy.REPLACE,
            request
        )
    }
}