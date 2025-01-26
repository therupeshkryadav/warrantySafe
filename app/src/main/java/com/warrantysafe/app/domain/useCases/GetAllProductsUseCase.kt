package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository
import com.warrantysafe.app.domain.utils.Results

class GetAllProductsUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(): Results<List<Product>> {
        return productRepository.getProducts()
    }
}