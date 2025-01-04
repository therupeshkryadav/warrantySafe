package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository

class AddProductUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(title: String, purchase: String, expiry: String, category: String, imageResId: Int) {
        val newProduct = Product(
            title = title,
            purchase = purchase,
            expiry = expiry,
            category = category,
            imageResId = imageResId
        )
        productRepository.addProduct(newProduct)
    }
}