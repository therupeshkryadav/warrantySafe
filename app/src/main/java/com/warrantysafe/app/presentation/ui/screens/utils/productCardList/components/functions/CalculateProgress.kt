package com.warrantysafe.app.presentation.ui.screens.utils.productCardList.components.functions

import android.annotation.SuppressLint
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@SuppressLint("NewApi")
fun calculateProgress(purchaseDate: String, expiryDate: String, currentDate: String): Float? {
    return try {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val purchase = LocalDate.parse(purchaseDate, formatter)
        val expiry = LocalDate.parse(expiryDate, formatter)
        val current = LocalDate.parse(currentDate, formatter)

        // Calculate the total warranty period and elapsed period
        val totalDays = ChronoUnit.DAYS.between(purchase, expiry).toFloat()
        val elapsedDays = ChronoUnit.DAYS.between(purchase, current).toFloat()

        // Ensure progress is within 0 to 1 range
        (elapsedDays / totalDays).coerceIn(0f, 1f)
    } catch (e: Exception) {
        // Handle any parsing or calculation errors
        null
    }
}


