package com.warrantysafe.app.presentation.common.components

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("ComposableNaming")
@Composable
fun navigationIcons(
    isSearch: Boolean,
    isAddWarranty: Boolean,
    isHomeorProfile: Boolean
) {
    if (isHomeorProfile.equals(true))
    {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Overflow Menu Icon"
            )
        }
    }
    else if(isSearch.equals(true))
    {

    }
    else if(isAddWarranty.equals(true))
    {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close Icon"
            )
        }
    }
}

@Preview
@Composable
fun navigationIconsPreview() {
    navigationIcons(
        isSearch = false,
        isAddWarranty = true,
        isHomeorProfile = false
    )
}