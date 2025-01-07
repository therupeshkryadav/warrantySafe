package com.warrantysafe.app.presentation.ui.screens.productCardList.components.functions

import android.annotation.SuppressLint
import android.util.Log
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@SuppressLint("NewApi")
fun periodCalculator(
    purchaseDate: String,
    expiryDate: String,
    currentDate: String
): String {
    try {
        // Correct format: "dd/MM/yyyy"
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val purchase = LocalDate.parse(purchaseDate, formatter)
        val expiry = LocalDate.parse(expiryDate, formatter)
        val current = LocalDate.parse(currentDate, formatter)

        // If the current date is after the expiry date
        if (current.isAfter(expiry)) {
            return "0 years, 0 months, 0 days"
        }

        // Calculate the difference in years, months, and days
        val years = ChronoUnit.YEARS.between(purchase, expiry).toInt()
        val months = ChronoUnit.MONTHS.between(purchase.plusYears(years.toLong()), expiry).toInt()
        val days = ChronoUnit.DAYS.between(
            purchase.plusYears(years.toLong()).plusMonths(months.toLong()), expiry
        ).toInt()

        // Return formatted period
        return "$years years, $months months, $days days"

     //   if(current date is bigger than expiry date then return  0 years 0 months 0 days)
    } catch (e: Exception) {
        Log.d("fatal","$e")// Handle invalid date formats gracefully
        return "Invalid date format. Please use the format: dd/MM/yyyy"
    }
}
