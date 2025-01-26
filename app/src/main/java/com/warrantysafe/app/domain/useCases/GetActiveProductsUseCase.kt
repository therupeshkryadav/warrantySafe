package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository
import com.warrantysafe.app.domain.utils.Results
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

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
                // Log the error or take appropriate action
                emptyList() // Return an empty list if fetching products fails
            }
            is Results.Loading -> {
                // Optionally handle a loading state if needed
                emptyList()
            }
        }
    }

    private fun isDateBefore(current: String, expiry: String): Boolean {
        val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return try {
            val currentDate = dateFormat.parse(current)
            val expiryDate = dateFormat.parse(expiry)
            expiryDate?.after(currentDate) ?: false
        } catch (e: ParseException) {
            false // Handle invalid date formats
        }
    }

    companion object {
        const val DATE_FORMAT = "dd/MM/yyyy" // Define a constant for date format
    }
}
