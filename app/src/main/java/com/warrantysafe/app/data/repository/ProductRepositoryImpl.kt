package com.warrantysafe.app.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository
import kotlinx.coroutines.tasks.await

class ProductRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : ProductRepository {

    private val usersCollection = firestore.collection("users")

    override suspend fun getProducts(): Result<List<Product>> {
        return try {
            val userId = firebaseAuth.currentUser?.uid

            if (userId != null) {
                // Fetch all documents in the "userProducts" subcollection for the current user
                val snapshot = usersCollection.document(userId).collection("userProducts").get().await()

                if (!snapshot.isEmpty) {
                    // Map each document to a Product object directly within the method
                    val products = snapshot.documents.mapNotNull { document ->
                        try {
                            Product(
                                id = document.getString("id") ?: "",
                                productName = document.getString("productName") ?: "",
                                purchase = document.getString("purchase") ?: "",
                                expiry = document.getString("expiry") ?: "",
                                category = document.getString("category") ?: "",
                                productImageUri = document.getString("productImgUri") ?: "", // Nullable
                                notes = document.getString("notes") ?: "" // Nullable
                            )
                        } catch (e: Exception) {
                            Log.e("ProductMapping", "Error mapping document to Product: ${e.localizedMessage}")
                            null // Skip this document if mapping fails
                        }
                    }
                    Result.success(products)
                } else {
                    // No products found for the user
                    Result.failure(Exception("No products found for the user"))
                }
            } else {
                // User not authenticated
                Result.failure(Exception("No authenticated user found"))
            }
        } catch (e: Exception) {
            // Handle Firestore-related errors
            Log.e("ProductRepo", "Error fetching products: ${e.localizedMessage}")
            Result.failure(e)
        }
    }


    override suspend fun addProduct(product: Product): Result<Product> {
        return try {
            // Ensure user is authenticated
            val userId = firebaseAuth.currentUser?.uid ?: return Result.failure(Exception("User not authenticated"))
            Log.d("ProductRepo", "user id fetched $userId $product")

            // Create a reference for the new product document
            val productRef = usersCollection.document(userId).collection("userProducts").document()

            // Prepare the product data
            val productData = mapOf(
                "id" to productRef.id,
                "productName" to product.productName,
                "productImgUri" to product.productImageUri,
                "category" to product.category,
                "purchase" to product.purchase,
                "expiry" to product.expiry,
                "notes" to product.notes
            )

            // Logging the product data for debugging
            Log.d("ProductRepo", "Product data to be saved: $productData")

            // Save the product data in the user's "userProducts" subcollection
            productRef.set(productData).await()

            // Success: Return the product object with the ID set
            Log.d("ProductRepo", "Product added successfully: ${productRef.id}")
            Result.success(product.copy(id = productRef.id))

        } catch (e: Exception) {
            // Logging the exception
            Log.e("ProductRepo", "Error adding product: ${e.localizedMessage}")
            Result.failure(e)
        }
    }

    override suspend fun updateProduct(product: Product): Result<Product> {
        return try {
            val userId = firebaseAuth.currentUser?.uid ?: return Result.failure(Exception("User not authenticated"))
            if (product.id.isNotEmpty()) {
                val productRef = usersCollection.document(userId).collection("userProducts").document(product.id)
                productRef.set(product).await()
                Result.success(product)
            } else {
                Result.failure(Exception("Product ID is empty"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteProducts(products: List<Product>) {
        val userId = firebaseAuth.currentUser?.uid ?: return
        val batch = firestore.batch()
        products.forEach { product ->
            if (product.id.isNotEmpty()) {
                val productRef = usersCollection.document(userId).collection("userProducts").document(product.id)
                batch.delete(productRef)
            }
        }
        batch.commit().await()
    }
}



