package com.warrantysafe.app.domain.model

import android.net.Uri

data class User(
    val fullName: String? = null, // Optional, can be null if not available
    val userName: String? = null, // Optional, can be null if not available
    val emailId: String,
    val profileImageUri: Uri,
    val phone: String? = null  // Optional, can be null if not available
)
