package com.warrantysafe.app.data.repository

import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository

class ProductRepositoryImpl : ProductRepository {

    private val productsList = mutableListOf<Product>(
        Product(
            productName = "Realme 3 Pro",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Electronics",
            notes = "First Notes served!!",
            imageResId = R.drawable.product_placeholder
        ),
        Product(
            productName = "Honda Sp 125 ",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Vehicles",
            imageResId = R.drawable.product_placeholder
        ),
        Product(
            productName = "Titan Watch",
            purchase = "01/10/2024",
            expiry = "01/10/2025",
            category = "Wearables",
            imageResId = R.drawable.product_placeholder
        ),
        Product(
            productName = "Rado Watch",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.product_placeholder
        ),
        Product(
            productName = "PS5",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.product_placeholder
        ),
        Product(
            productName = "LG Washing Machine ",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageResId = R.drawable.product_placeholder
        )
    )

//  private val productsList = mutableListOf<Product>()

    override suspend fun getProducts(): List<Product> {
        return productsList
    }

    override suspend fun addProduct(product: Product) {
        productsList.add(product)
    }
}
