package com.warrantysafe.app.utils

import android.content.Context
import android.util.Log
import androidx.work.*
import com.warrantysafe.app.data.worker.WarrantyWorker
import java.util.Calendar
import java.util.concurrent.TimeUnit

object WarrantyNotificationScheduler {

    private const val WORK_NAME_MORNING = "warranty_notification_morning"
    private const val WORK_NAME_EVENING = "warranty_notification_evening"
    private const val TAG = "WarrantyNotificationScheduler"

    fun schedule(context: Context) {
        Log.d(TAG, "Scheduling One-Time Work Requests for Twice a Day Execution")

        val workManager = WorkManager.getInstance(context)

        // Schedule Morning Work (8 AM)
        val morningDelay = calculateDelay(hours = 8, minutes = 0)
        val morningRequest = OneTimeWorkRequestBuilder<WarrantyWorker>()
            .setInitialDelay(morningDelay, TimeUnit.MILLISECONDS)
            .setConstraints(getWorkConstraints())
            .build()

        workManager.enqueueUniqueWork(WORK_NAME_MORNING, ExistingWorkPolicy.REPLACE, morningRequest)
        Log.d(TAG, "Morning Work Scheduled (Delay: ${morningDelay / 3600000} hours)")

        // Schedule Evening Work (4 PM)
        val eveningDelay = calculateDelay(hours = 16, minutes = 0)
        val eveningRequest = OneTimeWorkRequestBuilder<WarrantyWorker>()
            .setInitialDelay(eveningDelay, TimeUnit.MILLISECONDS)
            .setConstraints(getWorkConstraints())
            .build()

        workManager.enqueueUniqueWork(WORK_NAME_EVENING, ExistingWorkPolicy.REPLACE, eveningRequest)
        Log.d(TAG, "Evening Work Scheduled (Delay: ${eveningDelay / 3600000} hours)")
    }

    private fun calculateDelay(hours: Int, minutes: Int): Long {
        val now = Calendar.getInstance()
        val targetTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hours)
            set(Calendar.MINUTE, minutes)
            set(Calendar.SECOND, 0)
        }

        // If the target time has already passed today, schedule it for tomorrow
        if (targetTime.before(now)) {
            targetTime.add(Calendar.DAY_OF_MONTH, 1)
        }

        return targetTime.timeInMillis - now.timeInMillis
    }

    private fun getWorkConstraints(): Constraints {
        return Constraints.Builder()
            .setRequiresCharging(false)  // Run even if not charging
            .setRequiresBatteryNotLow(true)  // Avoid running when battery is low
            .build()
    }
}
