package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository

class GetProductDetailUseCase(private val productRepository: ProductRepository) {

    /**
     * Execute the use case to fetch product details.
     *
     * @param productId The ID of the product to fetch.
     * @return Result containing the product details or an error.
     */
    suspend operator fun invoke(productId: String): Result<Product> {
        if (productId.isBlank()) {
            return Result.failure(Exception("Product ID cannot be blank"))
        }
        return productRepository.getProductDetail(productId)
    }
}
