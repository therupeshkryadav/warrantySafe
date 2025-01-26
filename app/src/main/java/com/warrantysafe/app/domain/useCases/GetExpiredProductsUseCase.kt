package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository
import com.warrantysafe.app.domain.utils.Results
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class GetExpiredProductsUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(currentDate: String): List<Product> {
        return when (val result = productRepository.getProducts()) {
            is Results.Success -> {
                val products = result.data
                products.filter { product -> isExpired(currentDate, product.expiry) }
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

    /**
     * Helper function to check if a product's expiry date is before or equal to the current date.
     */
    private fun isExpired(current: String, expiry: String): Boolean {
        val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return try {
            val currentDate = dateFormat.parse(current)
            val expiryDate = dateFormat.parse(expiry)
            expiryDate?.before(currentDate) ?: false // Returns true if expiryDate is before currentDate
        } catch (e: ParseException) {
            false // Consider invalid dates as not expired
        }
    }

    companion object {
        const val DATE_FORMAT = "dd/MM/yyyy" // Shared date format
    }
}
