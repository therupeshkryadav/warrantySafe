package com.warrantysafe.app.utils

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.warrantysafe.app.data.worker.WarrantyWorker
import java.util.concurrent.TimeUnit

object WarrantyNotificationScheduler {
    fun schedule(context: Context) {
        val request = PeriodicWorkRequestBuilder<WarrantyWorker>(10, TimeUnit.MICROSECONDS)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "warranty_notification",
            ExistingPeriodicWorkPolicy.REPLACE,
            request
        )
    }
}