package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository
import java.text.SimpleDateFormat
import java.util.Locale

class GetActiveProductsUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(currentDate: String): List<Product> {
        // Fetch products and handle the result
        val result = productRepository.getProducts()

        return if (result.isSuccess) {
            val products = result.getOrDefault(emptyList())
            products.filter { product ->
                isDateBefore(currentDate, product.expiry)
            }
        } else {
            // Handle failure scenario, like logging or returning an empty list
            emptyList()
        }
    }

    private fun isDateBefore(current: String, expiry: String): Boolean {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val currentDate = dateFormat.parse(current)
        val expiryDate = dateFormat.parse(expiry)
        return expiryDate?.after(currentDate) ?: false
    }
}
