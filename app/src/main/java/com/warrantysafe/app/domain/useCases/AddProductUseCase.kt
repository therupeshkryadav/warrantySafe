package com.warrantysafe.app.domain.useCases

import android.net.Uri
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository

class AddProductUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(productName: String, purchase: String, expiry: String, category: String,notes: String, imageUri: Uri) {
        // Create new product
        val newProduct = Product(
            productId = generateProductId(), // Assume you have a method for generating unique IDs
            productName = productName,
            purchase = purchase,
            expiry = expiry,
            category = category,
            imageUri = imageUri,
            notes = notes
        )
        productRepository.addProduct(newProduct)
    }
}

// Utility to generate unique product IDs (UUID recommended)
private fun generateProductId(): String = java.util.UUID.randomUUID().toString()