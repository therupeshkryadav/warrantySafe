package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository

class UpdateProductUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(product: Product) : Result<Product>{
        return try {
            productRepository.updateProduct(product)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}