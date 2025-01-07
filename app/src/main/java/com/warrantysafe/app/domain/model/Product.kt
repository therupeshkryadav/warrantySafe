package com.warrantysafe.app.domain.model

data class Product(
    val productName: String,
    val purchase: String,
    val expiry: String,
    val category: String,
    val imageResId: Int,
    val notes: String? = null
)
