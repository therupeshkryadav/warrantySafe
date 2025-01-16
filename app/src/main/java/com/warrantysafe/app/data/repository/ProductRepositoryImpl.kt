package com.warrantysafe.app.data.repository

import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository


class ProductRepositoryImpl : ProductRepository {

    private val productsList = mutableListOf<Product>()

    override suspend fun getProducts(): List<Product> {
       return productsList
    }

    override suspend fun addProduct(product: Product) {
        productsList.add(product)
    }

    override suspend fun updateProduct(product: Product) {
        val index = productsList.indexOfFirst { it.productId == product.productId }
        if (index != -1) {
            productsList[index] = product
        }
    }

    override suspend fun deleteProducts(products: List<Product>) {
        productsList.removeAll(products)
    }
}

