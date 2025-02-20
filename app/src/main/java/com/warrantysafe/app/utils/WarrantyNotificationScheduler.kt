package com.warrantysafe.app.utils

import android.content.Context
import android.util.Log
import androidx.work.*
import com.warrantysafe.app.data.worker.WarrantyWorker
import java.util.Calendar
import java.util.concurrent.TimeUnit

object WarrantyNotificationScheduler {

    private const val WORK_NAME_DAILY = "warranty_notification_daily"
    private const val TAG = "WarrantyNotificationScheduler"

    fun schedule(context: Context) {
        Log.d(TAG, "Scheduling Warranty Reminder Notification (Once a Day)")

        val workManager = WorkManager.getInstance(context)

        val dailyDelay = calculateDelay(hours = 9, minutes = 0) // Default at 9 AM

        val dailyRequest = OneTimeWorkRequestBuilder<WarrantyWorker>()
            .setInitialDelay(dailyDelay, TimeUnit.MILLISECONDS)
            .setConstraints(getWorkConstraints())
            .build()

        workManager.enqueueUniqueWork(WORK_NAME_DAILY, ExistingWorkPolicy.KEEP, dailyRequest)

        Log.d(TAG, "Warranty Reminder Scheduled (Delay: ${dailyDelay / 3600000} hours)")
    }

    private fun calculateDelay(hours: Int, minutes: Int): Long {
        val now = Calendar.getInstance()
        val targetTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hours)
            set(Calendar.MINUTE, minutes)
            set(Calendar.SECOND, 0)
        }

        if (targetTime.before(now)) {
            targetTime.add(Calendar.DAY_OF_MONTH, 1)
        }

        return targetTime.timeInMillis - now.timeInMillis
    }

    private fun getWorkConstraints(): Constraints {
        return Constraints.Builder()
            .setRequiresBatteryNotLow(true) // Avoid running on low battery
            .build()
    }
}
