package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository

class DeleteProductsUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(products: List<Product>) = productRepository.deleteProducts(products)
}