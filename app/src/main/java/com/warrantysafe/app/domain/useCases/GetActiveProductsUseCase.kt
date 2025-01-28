package com.warrantysafe.app.domain.useCases

import android.annotation.SuppressLint
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository
import com.warrantysafe.app.domain.utils.Results
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class GetActiveProductsUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(currentDate: String): List<Product> {
        return when (val result = productRepository.getProducts()) {
            is Results.Success -> {
                val products = result.data
                products.filter { product -> isDateBefore(currentDate, product.expiry) }
            }
            is Results.Failure -> {
                // Log the error or handle failure as needed
                emptyList()
            }
            is Results.Loading -> {
                // Handle loading state if necessary
                emptyList()
            }
        }
    }

    @SuppressLint("NewApi")
    private fun isDateBefore(current: String, expiry: String): Boolean {
        val dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
        return try {
            val currentDate = LocalDate.parse(current, dateFormatter)
            val expiryDate = LocalDate.parse(expiry, dateFormatter)
            expiryDate.isAfter(currentDate) // Check if expiry is after current date
        } catch (e: DateTimeParseException) {
            // Log error (you could also throw a custom exception or handle it in other ways)
            false
        }
    }

    companion object {
        const val DATE_FORMAT = "dd/MM/yyyy" // Define a constant for date format
    }
}
