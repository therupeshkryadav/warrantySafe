package com.warrantysafe.app.data.models

import android.net.Uri
import com.warrantysafe.app.domain.model.Product

// Firestore Model
data class ProductFirestore(
    val productName: String = "",
    val purchase: String = "", // Date as String ("yyyy-MM-dd")
    val expiry: String = "",   // Date as String ("yyyy-MM-dd")
    val category: String = "",
    val imageUri: String = "", // Image URI as String (stored as a URL or path)
    val notes: String? = null  // Optional notes
) {
    // Convert Firestore model to domain model
    fun toDomainModel(productId: String) = Product(
        productId = productId,
        productName = productName,
        purchase = purchase,
        expiry = expiry,
        category = category,
        imageUri = Uri.parse(imageUri), // Convert imageUri to Uri
        notes = notes
    )
}
