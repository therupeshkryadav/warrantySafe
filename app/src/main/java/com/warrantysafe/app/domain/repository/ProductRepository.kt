package com.warrantysafe.app.domain.repository

import com.warrantysafe.app.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun addProduct(product: Product)
    suspend fun updateProduct(product: Product)
    suspend fun deleteProducts(products: List<Product>)
}