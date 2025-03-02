package com.warrantysafe.app.data.worker

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.warrantysafe.app.domain.useCases.GetAllProductsUseCase
import com.warrantysafe.app.domain.utils.Results
import com.warrantysafe.app.utils.NotificationHelper
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class WarrantyWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params), KoinComponent {

    private val getAllProductsUseCase: GetAllProductsUseCase by inject()

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        return try {
            val result = getAllProductsUseCase() // Fetch all products

            if (result is Results.Success) {
                val today = LocalDate.now()
                val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                val products = result.data

                val expiringProducts = products.filter { product ->
                    try {
                        val expiryDate = product.expiry // Assuming expiry is a String in "dd/MM/yyyy"
                        if (expiryDate.isNullOrEmpty()) return@filter false

                        val expiry = LocalDate.parse(expiryDate, formatter)
                        val daysToExpiry = ChronoUnit.DAYS.between(today, expiry) // Proper date calculation

                        daysToExpiry in 0..30
                    } catch (e: Exception) {
                        Log.e("WarrantyWorker", "Error parsing expiry date", e)
                        false
                    }
                }

                if (expiringProducts.isNotEmpty()) {
                    val notificationText = expiringProducts.joinToString("\n") { product ->
                        val expiry = LocalDate.parse(product.expiry, formatter)
                        val daysRemaining = ChronoUnit.DAYS.between(today, expiry)
                        "${product.productName} is expiring in ${daysRemaining+1} days!"
                    }

                    NotificationHelper.sendNotification(applicationContext, "Expiring Products", notificationText)

                    expiringProducts.forEach { product ->
                        Log.d("WarrantyWorker", "Expiring Warranty: ${product.productName}")
                    }
                }

                Result.success()
            } else {
                Result.failure()
            }
        } catch (e: Exception) {
            Log.e("WarrantyWorker", "Error checking expiring warranties", e)
            Result.failure()
        }
    }
}
