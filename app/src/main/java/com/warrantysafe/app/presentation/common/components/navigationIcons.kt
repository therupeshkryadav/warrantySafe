package com.warrantysafe.app.presentation.common.components

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R

@SuppressLint("ComposableNaming")
@Composable
fun navigationIcons(
    navController: NavHostController,
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
    else if(isAddWarranty.equals(true))
    {
        IconButton(onClick = {navController.popBackStack()}) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close Icon"
            )
        }
    }
    else if(isSearch.equals(true))
    {
        IconButton(onClick = {navController.popBackStack()}) {
            Icon(
                painter = androidx.compose.ui.res.painterResource(id = R.drawable.arrow_back),
                contentDescription = "Back",
                tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview
@Composable
fun navigationIconsPreview() {
    navigationIcons(
        navController = rememberNavController(),
        isSearch = true,
        isAddWarranty = false,
        isHomeorProfile = false
    )
}