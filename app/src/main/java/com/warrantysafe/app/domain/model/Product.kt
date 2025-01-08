package com.warrantysafe.app.domain.model

import androidx.compose.ui.graphics.painter.Painter

data class Product(
    val productName: String,
    val purchase: String,
    val expiry: String,
    val category: String,
    val imageResource: Painter,
    val notes: String? = null
)
