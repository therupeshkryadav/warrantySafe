package com.warrantysafe.app.domain.model

data class Product(
    val id: String = "",
    val productName: String,
    val purchase: String,
    val expiry: String,
    val category: String,
    val productImageUri: String,
    val receiptImageUri: String,
    val notes: String
)



