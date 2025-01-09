package com.warrantysafe.app.domain.useCases

import android.net.Uri
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository

class AddProductUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(productName: String, purchase: String, expiry: String, category: String,notes: String, imageUri: Uri) {
        val newProduct = Product(
            productName = productName,
            purchase = purchase,
            expiry = expiry,
            category = category,
            notes = notes,
            imageUri = imageUri
        )
        productRepository.addProduct(newProduct)
    }
}