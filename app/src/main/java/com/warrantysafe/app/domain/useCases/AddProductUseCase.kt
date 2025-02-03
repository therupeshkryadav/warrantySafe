package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository

class AddProductUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(product: Product) {
        // Create new product
        val newProduct = Product(
            productName = product.productName,
            purchase = product.purchase,
            expiry = product.expiry,
            category = product.category,
            productImageUri = product.productImageUri,
            notes = product.notes
        )
        productRepository.addProduct(newProduct)
    }
}