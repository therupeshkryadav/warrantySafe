package com.warrantysafe.app.domain.repository

import com.warrantysafe.app.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): Result<List<Product>>
    suspend fun getProductDetail(productId: String) : Result<Product>
    suspend fun addProduct(product: Product) : Result<Product>
    suspend fun updateProduct(product: Product) : Result<Product>
    suspend fun deleteProducts(products: List<Product>)
}