package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository
import com.warrantysafe.app.domain.utils.Results

class UpdateProductUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(product: Product) : Results<Product> {
        return try {
            productRepository.updateProduct(product)
        } catch (e: Exception) {
            Results.Failure(e)
        }
    }
}