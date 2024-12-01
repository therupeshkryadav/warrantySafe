package com.warrantysafe.app.presentation.common.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun actions(
    isSearch: Boolean,
    isAddWarranty: Boolean,
    isHomeorProfile: Boolean
) {
    Row {
        if (isHomeorProfile.equals(true))
        {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Overflow Menu Icon"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
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
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Check Icon"
                )
            }
        }
    }
}

@Preview
@Composable
fun actionsPreview() {
    actions(
        isSearch = false,
        isAddWarranty = true,
        isHomeorProfile = false
    )
}