package com.warrantysafe.app.domain.model

import android.net.Uri

data class Product(
    val id: String = "",
    val productName: String,
    val purchase: String,
    val expiry: String,
    val category: String,
    val productImageUri: String,
    val notes: String
)



