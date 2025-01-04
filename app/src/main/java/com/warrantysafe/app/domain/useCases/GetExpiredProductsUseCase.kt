package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository
import java.text.SimpleDateFormat
import java.util.Locale

class GetExpiredProductsUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(currentDate: String): List<Product> {
        val products = productRepository.getProducts()
        return products.filter { product ->
            !isDateBefore(currentDate, product.expiry)
        }
    }

    private fun isDateBefore(current: String, expiry: String): Boolean {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val currentDate = dateFormat.parse(current)
        val expiryDate = dateFormat.parse(expiry)
        return expiryDate?.after(currentDate) ?: false
    }
}
