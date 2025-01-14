package com.warrantysafe.app.data.repository

import android.net.Uri
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository

class ProductRepositoryImpl : ProductRepository {

    private val productsList = mutableListOf<Product>(
        Product(
            productId = "1",
            productName = "Realme 3 Pro",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Electronics",
            notes = "First Notes served!!",
            imageUri = Uri.parse("android.resource://com.warrantysafe.app/${R.drawable.product_placeholder}")
        ),
        Product(
            productId = "2",
            productName = "Honda Sp 125 ",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Vehicles",
            imageUri = Uri.parse("android.resource://com.warrantysafe.app/${R.drawable.product_placeholder}")
        ),
        Product(
            productId = "3",
            productName = "Titan Watch",
            purchase = "01/10/2024",
            expiry = "01/10/2025",
            category = "Wearables",
            imageUri = Uri.parse("android.resource://com.warrantysafe.app/${R.drawable.product_placeholder}")
        ),
        Product(
            productId = "4",
            productName = "Rado Watch",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageUri = Uri.parse("android.resource://com.warrantysafe.app/${R.drawable.product_placeholder}")
        ),
        Product(
            productId = "5",
            productName = "PS5",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageUri = Uri.parse("android.resource://com.warrantysafe.app/${R.drawable.product_placeholder}")
        ),
        Product(
            productId = "6",
            productName = "LG Washing Machine ",
            purchase = "30/11/2023",
            expiry = "01/12/2024",
            category = "Electronics",
            imageUri = Uri.parse("android.resource://com.warrantysafe.app/${R.drawable.product_placeholder}")
        ),
        Product(
            productId = "7",
            productName = "Honda Sp 125 ",
            purchase = "30/11/2024",
            expiry = "30/11/2025",
            category = "Vehicles",
            imageUri = Uri.parse("android.resource://com.warrantysafe.app/${R.drawable.product_placeholder}")
        )
    )

 // private val productsList = mutableListOf<Product>()

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
