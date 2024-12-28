package com.warrantysafe.app.presentation.common.productList.components.productCard.components

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@SuppressLint("NewApi")
@Composable
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
        // Handle invalid date formats gracefully
        return "Invalid date format. Please use the format: dd/MM/yyyy"
    }
}
