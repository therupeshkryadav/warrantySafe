package com.warrantysafe.app.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.warrantysafe.app.data.models.ProductFirestore
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.extensions.toFirestoreMap
import com.warrantysafe.app.domain.repository.ProductRepository
import kotlinx.coroutines.tasks.await


class ProductRepositoryImpl(
    private val firestore: FirebaseFirestore
) : ProductRepository {

    private val userId = FirebaseAuth.getInstance().currentUser?.uid!!
    private val productsCollection = firestore.collection("users").document(userId).collection("products")

    override suspend fun getProducts(): List<Product> {
        return try {
            val snapshot = productsCollection.get().await()
            snapshot.documents.mapNotNull { it.toObject<ProductFirestore>()?.toDomainModel(it.id) }
        } catch (e: Exception) {
            throw RuntimeException("Failed to fetch products: ${e.message}")
        }
    }

    override suspend fun addProduct(product: Product) {
        try {
            productsCollection
                .document(product.productId)
                .set(product.toFirestoreMap())
                .await()
        } catch (e: Exception) {
            throw RuntimeException("Failed to add product: ${e.message}")
        }
    }

    override suspend fun updateProduct(product: Product) {
        try {
            productsCollection
                .document(product.productId)
                .set(product.toFirestoreMap())
                .await()
        } catch (e: Exception) {
            throw RuntimeException("Failed to update product: ${e.message}")
        }
    }

    override suspend fun deleteProducts(products: List<Product>) {
        try {
            val batch = firestore.batch()
            products.forEach { product ->
                val docRef = productsCollection.document(product.productId)
                batch.delete(docRef)
            }
            batch.commit().await()
        } catch (e: Exception) {
            throw RuntimeException("Failed to delete products: ${e.message}")
        }
    }
}

