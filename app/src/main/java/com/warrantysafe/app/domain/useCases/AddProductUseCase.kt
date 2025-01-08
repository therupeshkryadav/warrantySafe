package com.warrantysafe.app.domain.useCases

import androidx.compose.ui.graphics.painter.Painter
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository

class AddProductUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(productName: String, purchase: String, expiry: String, category: String,notes: String, imageResource: Painter) {
        val newProduct = Product(
            productName = productName,
            purchase = purchase,
            expiry = expiry,
            category = category,
            notes = notes,
            imageResource = imageResource
        )
        productRepository.addProduct(newProduct)
    }
}