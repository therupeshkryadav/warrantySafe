package com.warrantysafe.app.utils

import android.content.Context
import android.util.Log
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.warrantysafe.app.data.worker.WarrantyWorker
import java.util.concurrent.TimeUnit

object WarrantyNotificationScheduler {

    private const val WORK_NAME = "warranty_notification"
    private const val TAG = "WarrantyNotificationScheduler"

    fun schedule(context: Context) {
        // Log when scheduling starts
        Log.d(TAG, "Scheduling One-Time Work Request")

//        // One-Time Request (For testing purpose)
//        val testRequest = OneTimeWorkRequestBuilder<WarrantyWorker>().build()
//        WorkManager.getInstance(context).enqueue(testRequest)
//        Log.d(TAG, "One-Time Work Request enqueued: ID = ${testRequest.id}")

        // Log when periodic scheduling starts
        Log.d(TAG, "Scheduling Periodic Work Request")

        // Periodic Work Request (Every 15 minutes)
        val request = PeriodicWorkRequestBuilder<WarrantyWorker>(2, TimeUnit.DAYS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiresCharging(false)  // Run even if not charging
                    .setRequiresBatteryNotLow(true)  // Avoid running when battery is low
                    .build()
            )
            .build()

        // Enqueue the periodic work request
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
        Log.d(TAG, "Periodic Work Request enqueued: ID = ${request.id}")

        // Check and Log the status of the work request
        WorkManager.getInstance(context).getWorkInfoByIdLiveData(request.id).observeForever { workInfo ->
            workInfo?.let {
                Log.d(TAG, "Work Request Status: ${it.state}")
            }
        }
    }
}


