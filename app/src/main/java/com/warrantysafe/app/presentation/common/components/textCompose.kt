package com.warrantysafe.app.presentation.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun textCompose(
    isSearch: Boolean,
    isAddWarranty: Boolean,
    isHomeorProfile: Boolean
) {
    if (isHomeorProfile.equals(true))
    {
        Text(
            text = "",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth() // Center-aligns the title
        )
    }
    else if(isSearch.equals(true))
    {

    }
    else if(isAddWarranty.equals(true))
    {
        Text(
            text = "Add Warranty",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth() // Center-aligns the title
        )
    }
}