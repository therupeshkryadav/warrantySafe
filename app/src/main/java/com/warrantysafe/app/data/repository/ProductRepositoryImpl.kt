package com.warrantysafe.app.data.repository

import android.content.ContentResolver
import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository
import com.warrantysafe.app.domain.utils.Results
import io.appwrite.Client
import io.appwrite.ID
import io.appwrite.models.InputFile
import io.appwrite.services.Storage
import kotlinx.coroutines.tasks.await
import java.io.File

class ProductRepositoryImpl(
    private val context: Context,
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val appwriteClient: Client,
    private val appwriteStorage: Storage
) : ProductRepository {

    private val usersCollection = firestore.collection("users")

    override suspend fun getProducts(): Results<List<Product>> {
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
                    Results.Success(products)
                } else {
                    // No products found for the user
                    Results.Failure(Exception("No products found for the user"))
                }
            } else {
                // User not authenticated
                Results.Failure(Exception("No authenticated user found"))
            }
        } catch (e: Exception) {
            // Handle Firestore-related errors
            Log.e("ProductRepo", "Error fetching products: ${e.localizedMessage}")
            Results.Failure(e)
        }
    }

    override suspend fun getProductDetail(productId: String): Results<Product> {
        return try {
            val userId = firebaseAuth.currentUser?.uid ?: return Results.Failure(Exception("User not authenticated"))

            // Fetch the product document by its ID
            val productSnapshot = usersCollection
                .document(userId)
                .collection("userProducts")
                .document(productId)
                .get()
                .await()

            // Check if the document exists
            if (productSnapshot.exists()) {
                val product = Product(
                    id = productSnapshot.getString("id") ?: "",
                    productName = productSnapshot.getString("productName") ?: "",
                    purchase = productSnapshot.getString("purchase") ?: "",
                    expiry = productSnapshot.getString("expiry") ?: "",
                    category = productSnapshot.getString("category") ?: "",
                    productImageUri = productSnapshot.getString("productImgUri") ?: "",
                    notes = productSnapshot.getString("notes") ?: ""
                )
                Results.Success(product)
            } else {
                Results.Failure(Exception("Product not found"))
            }
        } catch (e: Exception) {
            Log.e("ProductRepo", "Error fetching product: ${e.localizedMessage}")
            Results.Failure(e)
        }
    }

    override suspend fun addProduct(product: Product): Results<Product> {
        return try {
            // Ensure user is authenticated
            val userId = firebaseAuth.currentUser?.uid ?: return Results.Failure(Exception("User not authenticated"))
            Log.d("ProductRepo", "user id fetched $userId $product")

            // Upload profile image to Appwrite (if provided)
            val productImageUri = product.productImageUri
            val uploadedProductImageUrl = if (productImageUri.isNotEmpty()) {
                uploadProfileImageToAppwrite(productImageUri)
            } else {
                ""
            }

            // Create a reference for the new product document
            val productRef = usersCollection.document(userId).collection("userProducts").document()

            // Prepare the product data
            val productData = mapOf(
                "id" to productRef.id,
                "productName" to product.productName,
                "productImgUri" to uploadedProductImageUrl,
                "category" to product.category,
                "purchase" to product.purchase,
                "expiry" to product.expiry,
                "notes" to product.notes
            )

            // Logging the product data for debugging
            Log.d("ProductRepo", "Product data to be saved: $productData")

            // Save the product data in the user's "userProducts" collection
            productRef.set(productData).await()

            // Success: Return the product object with the ID set
            Log.d("ProductRepo", "Product added successfully: ${productRef.id}")
            Results.Success(product.copy(id = productRef.id))

        } catch (e: Exception) {
            // Logging the exception
            Log.e("ProductRepo", "Error adding product: ${e.localizedMessage}")
            Results.Failure(e)
        }
    }

    override suspend fun updateProduct(product: Product): Results<Product> {
        return try {
            val userId = firebaseAuth.currentUser?.uid ?: return Results.Failure(Exception("User not authenticated"))
            if (product.id.isNotEmpty()) {
                val productRef = usersCollection.document(userId).collection("userProducts").document(product.id)

                // Upload profile image to Appwrite (if provided)
                val productImageUri = product.productImageUri
                val updatedProductImageUrl = if (productImageUri.isNotEmpty()) {
                    uploadProfileImageToAppwrite(productImageUri)
                } else {
                    ""
                }

                // Prepare the product data
                val productData = mapOf(
                    "id" to productRef.id,
                    "productName" to product.productName,
                    "productImgUri" to updatedProductImageUrl,
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
                Results.Success(product)
            } else {
                Results.Failure(Exception("Product ID is empty"))
            }
        } catch (e: Exception) {
            Results.Failure(e)
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

    /**
     * Helper function to upload a profile image to Appwrite storage
     */
    private suspend fun uploadProfileImageToAppwrite(uri: String): String {
        return try {
            // Resolve the content:// URI using ContentResolver
            val contentResolver: ContentResolver = context.contentResolver
            val tempFile = File.createTempFile("profile_image", ".jpg", context.cacheDir)

            // Open the input stream and copy to the temporary file
            contentResolver.openInputStream(android.net.Uri.parse(uri)).use { inputStream ->
                tempFile.outputStream().use { outputStream ->
                    inputStream?.copyTo(outputStream)
                }
            }

            // Upload the temporary file to Appwrite storage
            val file = appwriteStorage.createFile(
                bucketId = "678fd444002f3d3c4897",
                fileId = ID.unique(),
                file = InputFile.fromFile(tempFile)
            )

            // Return the file's public URL
            "${appwriteClient.endPoint}/storage/buckets/${file.bucketId}/files/${file.id}/view?project=warranty-safe&project=warranty-safe&mode=admin"
        } catch (e: Exception) {
            Log.e("AppwriteUpload", "Error uploading profile image", e)
            ""
        }
    }
}



