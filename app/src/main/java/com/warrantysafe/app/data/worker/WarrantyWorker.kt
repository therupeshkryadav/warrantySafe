package com.warrantysafe.app.data.worker

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.warrantysafe.app.domain.useCases.GetExpiringWarrantiesUseCase
import com.warrantysafe.app.domain.utils.Results
import com.warrantysafe.app.utils.NotificationHelper
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WarrantyWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params), KoinComponent {

    private val getExpiringWarrantiesUseCase: GetExpiringWarrantiesUseCase by inject()
// "AIzaSyC8O9mHdE8NOVGuzSSNyHX-YWcwuzN0Cas"
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        try {
            val result = getExpiringWarrantiesUseCase(30) // Check for warranties expiring in 30 days

            if (result is Results.Success) {
                val expiringWarranties = result.data
                if (expiringWarranties.isNotEmpty()) {
                    // Send notification for expiring warranties
                    expiringWarranties.forEach { product ->
                        NotificationHelper.sendNotification(applicationContext,"Expiry Alert","${product.productName} is expiring within Some Days!!")
                        Log.d("WarrantyWorker", "Expiring Warranty: ${product.productName}")
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("WarrantyWorker", "Error checking expiring warranties", e)
            return Result.failure()
        }

        return Result.success()
    }
}