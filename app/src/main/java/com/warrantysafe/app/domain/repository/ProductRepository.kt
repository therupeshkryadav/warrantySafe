package com.warrantysafe.app.domain.repository

import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.utils.Results

interface ProductRepository {
    suspend fun getProducts(): Results<List<Product>>
    suspend fun getProductDetail(productId: String) : Results<Product>
    suspend fun addProduct(product: Product) : Results<Product>
    suspend fun updateProduct(product: Product) : Results<Product>
    suspend fun deleteProducts(products: List<Product>)
}