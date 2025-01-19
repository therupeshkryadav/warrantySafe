package com.warrantysafe.app.domain.useCases

import android.net.Uri
import android.util.Log
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository

class AddProductUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(productName: String, purchase: String, expiry: String, category: String, notes: String, productImageUri: Uri) {
        // Create new product
        val newProduct = Product(
            productName = productName,
            purchase = purchase,
            expiry = expiry,
            category = category,
            productImageUri = productImageUri,
            notes = notes
        )
        Log.d("ProductID", generateProductId())
        productRepository.addProduct(newProduct)
    }
}

// Utility to generate unique product IDs (UUID recommended)
private fun generateProductId(): String = java.util.UUID.randomUUID().toString()