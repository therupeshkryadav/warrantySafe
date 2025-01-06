package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository

class GetAllProductsUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(): List<Product> {
        return productRepository.getProducts()
    }
}