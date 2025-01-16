package com.warrantysafe.app.domain.extensions

import com.warrantysafe.app.domain.model.Product

// Extension function to convert a Product domain model to a Firestore map
fun Product.toFirestoreMap(): Map<String, Any?> {
    return mapOf(
        "productName" to productName,
        "purchase" to purchase,
        "expiry" to expiry,
        "category" to category,
        "imageUri" to imageUri.toString(), // Convert Uri to String
        "notes" to notes
    )
}