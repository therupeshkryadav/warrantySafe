package com.warrantysafe.app.utils

import android.content.Context
import android.util.Log
import androidx.work.*
import com.warrantysafe.app.data.worker.WarrantyWorker
import java.util.Calendar
import java.util.concurrent.TimeUnit

object WarrantyNotificationScheduler {

    private const val WORK_NAME_DAILY = "warranty_notifications_daily"
    private const val TAG = "WarrantyScheduler"

    fun schedule(context: Context) {
        Log.d(TAG, "Scheduling Warranty Reminder Notification (Once a Day)")

        val workManager = WorkManager.getInstance(context)

        val initialDelay = calculateDelay() // delay for 6 hours

        val dailyRequest = OneTimeWorkRequestBuilder<WarrantyWorker>()
            .setInitialDelay(initialDelay, TimeUnit.HOURS) // Wait for 'initialDelay' hours before executing
            .setConstraints(getWorkConstraints()) // Apply conditions like charging, network, etc.
            .build()




        workManager.enqueueUniqueWork(WORK_NAME_DAILY, ExistingWorkPolicy.REPLACE, dailyRequest)

        Log.d(TAG, "Warranty Reminder Scheduled with delay of 6 hours")
    }

    private fun calculateDelay(): Long {
        val now = Calendar.getInstance()
        val targetTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 6)
            set(Calendar.MINUTE, 0)
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
