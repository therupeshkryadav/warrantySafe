package com.warrantysafe.app.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository
import kotlinx.coroutines.tasks.await

class ProductRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : ProductRepository {

    private val usersCollection = firestore.collection("products")

    private val productsList = mutableListOf<Product>()

    override suspend fun getProducts(): List<Product> {
        return productsList
    }

    override suspend fun addProduct(product: Product): Result<Product> {
        return try {
            val firebaseUser = firebaseAuth.currentUser
            if (firebaseUser != null) {
                // Save user data to Firestore
                val userId = firebaseUser.uid
                val productData = mapOf(
                    "productName" to product.productName,
                    "category" to product.category,
                    "purchase" to product.purchase,
                    "expiry" to product.expiry,
                    "productImageUri" to product.productImageUri,
                    "notes" to product.notes
                )
                usersCollection.document(userId).set(productData).await()
                Result.success(product)
            } else {
                Result.failure(Exception("User creation failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateProduct(product: Product) : Result<Product>{
        return try {
            val userId = firebaseAuth.currentUser?.uid
            if (userId != null) {
                val productData = mapOf(
                    "productName" to product.productName,
                    "category" to product.category,
                    "purchase" to product.purchase,
                    "expiry" to product.expiry,
                    "productImageUri" to product.productImageUri,
                    "notes" to product.notes
                )
                usersCollection.document(userId).update(productData).await()
                Result.success(product)
            } else {
                Result.failure(Exception("No authenticated user found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteProducts(products: List<Product>) {
        productsList.removeAll(products)
    }
}

