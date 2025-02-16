package com.warrantysafe.app.data.repository

import android.content.ContentResolver
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.pdf.PdfDocument
import android.net.Uri
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

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
                                receiptImageUri = document.getString("receiptImgUri")?: "",
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

    override suspend fun searchProducts(query: String): List<Product> {
        return try {
            val userId = firebaseAuth.currentUser?.uid ?: return emptyList()

            // Convert query to lowercase for case-insensitive search
            val queryLower = query.lowercase()

            // Search in productName, category, and notes fields
            val productQuery = usersCollection
                .document(userId)
                .collection("userProducts")
                .whereGreaterThanOrEqualTo("productName", queryLower)
                .whereLessThanOrEqualTo("productName", queryLower + '\uf8ff')
                .get()
                .await()

            val products = productQuery.documents.mapNotNull { document ->
                try {
                    Product(
                        id = document.getString("id") ?: "",
                        productName = document.getString("productName") ?: "",
                        purchase = document.getString("purchase") ?: "",
                        expiry = document.getString("expiry") ?: "",
                        category = document.getString("category") ?: "",
                        productImageUri = document.getString("productImgUri") ?: "",
                        receiptImageUri = document.getString("receiptImgUri")?: "",
                        notes = document.getString("notes") ?: ""
                    )
                } catch (e: Exception) {
                    Log.e("ProductMapping", "Error mapping document to Product: ${e.localizedMessage}")
                    null // Skip this document if mapping fails
                }
            }

            // Optionally, filter further in-memory to match category and notes
            products.filter { product ->
                product.productName.contains(query, ignoreCase = true) ||
                        product.category.contains(query, ignoreCase = true) ||
                        product.notes.contains(query, ignoreCase = true)
            }
        } catch (e: Exception) {
            Log.e("ProductRepo", "Error searching products: ${e.localizedMessage}")
            emptyList()
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
                    receiptImageUri = productSnapshot.getString("receiptImgUri")?: "",
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
            Log.d("ProductRepo", "User ID fetched: $userId, Product: $product")

            // Upload profile image to Appwrite (if provided)
            val productImageUri = product.productImageUri?:""
            val uploadedProductImageUrl = if (productImageUri.isNotEmpty()) {
                uploadProductImageToAppwrite(productImageUri)
            } else {
                ""
            }

            // Convert receipt image to PDF and upload to Appwrite (if provided)
            val receiptImageUri = product.receiptImageUri?:""
            val uploadedReceiptImagePdfUrl = if (receiptImageUri.isNotEmpty()) {
                convertImageToPdfAndUpload(receiptImageUri)
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
                "receiptImgUri" to uploadedReceiptImagePdfUrl,
                "category" to product.category,
                "purchase" to product.purchase,
                "expiry" to product.expiry,
                "notes" to product.notes
            )

            // Logging the product data for debugging
            Log.d("ProductRepo", "Product data to be saved: $productData")

            // Use suspendCoroutine to correctly return success or failure
            return suspendCoroutine { continuation ->
                productRef.set(productData)
                    .addOnSuccessListener {
                        Log.d("ProductRepo", "Product added successfully: ${productRef.id}")
                        continuation.resume(Results.Success(product.copy(id = productRef.id)))
                    }
                    .addOnFailureListener { e ->
                        Log.e("ProductRepo", "Error adding product: ${e.localizedMessage}")
                        continuation.resume(Results.Failure(e))
                    }
            }

        } catch (e: Exception) {
            // Logging the exception
            Log.e("ProductRepo", "Unexpected error: ${e.localizedMessage}")
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
                    uploadProductImageToAppwrite(productImageUri)
                } else {
                    ""
                }

                // Convert receipt image to PDF and upload to Appwrite (if provided)
                val receiptImageUri = product.receiptImageUri
                val updatedReceiptImagePdfUrl = if (receiptImageUri.isNotEmpty()) {
                    convertImageToPdfAndUpload(receiptImageUri)
                } else {
                    ""
                }

                // Prepare the product data
                val productData = mapOf(
                    "id" to productRef.id,
                    "productName" to product.productName,
                    "receiptImgUri" to updatedReceiptImagePdfUrl,
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
    private suspend fun uploadProductImageToAppwrite(uri: String): String {
        return try {
            // Resolve the content:// URI using ContentResolver
            val contentResolver: ContentResolver = context.contentResolver
            val tempFile = withContext(Dispatchers.IO) {
                File.createTempFile("product_image", ".jpg", context.cacheDir)
            }

            // Open the input stream and copy to the temporary file
            contentResolver.openInputStream(Uri.parse(uri)).use { inputStream ->
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
            Log.e("AppwriteUpload", "Error uploading product image $uri", e)
            ""
        }
    }

    private suspend fun convertImageToPdfAndUpload(imageUri: String): String {
        var pdfFile: File? = null
        return try {
            // Convert Image to PDF
            val inputStream = context.contentResolver.openInputStream(Uri.parse(imageUri))
            val bitmap = BitmapFactory.decodeStream(inputStream)

            pdfFile = File(context.cacheDir, "receipt_${System.currentTimeMillis()}.pdf")
            val pdfDocument = PdfDocument()
            val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, 1).create()
            val page = pdfDocument.startPage(pageInfo)
            page.canvas.drawBitmap(bitmap, 0f, 0f, null)
            pdfDocument.finishPage(page)

            withContext(Dispatchers.IO) {
                FileOutputStream(pdfFile).use {
                    pdfDocument.writeTo(it)
                }
            }
            pdfDocument.close()

            // Upload PDF to Appwrite
            val response = appwriteStorage.createFile(
                bucketId = "678fd444002f3d3c4897",
                fileId = ID.unique(),
                file = InputFile.fromFile(pdfFile)
            )
            Log.d("ProductRepo", "PDF Uploaded: ${response.toMap()}")

            "${appwriteClient.endPoint}/storage/buckets/${response.bucketId}/files/${response.id}/view?project=warranty-safe&project=warranty-safe&mode=admin"
        } catch (e: Exception) {
            Log.e("ProductRepo", "Error converting image to PDF or uploading: ${e.localizedMessage}")
            ""
        } finally {
            // Delete local PDF file after uploading
            pdfFile?.delete()
        }
    }
}



