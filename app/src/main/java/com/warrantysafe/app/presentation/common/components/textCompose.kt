package com.warrantysafe.app.presentation.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import com.warrantysafe.app.R

@OptIn(ExperimentalMaterial3Api::class)
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
    else if(isAddWarranty.equals(true))
    {
        Text(
            text = "Add Warranty",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth() // Center-aligns the title
        )
    }
    else if(isSearch.equals(true))
    {
        var text by remember { mutableStateOf("") }
        TextField(
            value = text,
            onValueChange = { text = it },
            enabled = true,
            placeholder = {
                Text(
                    text = "Search",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyMedium,
            colors = androidx.compose.material3.TextFieldDefaults.textFieldColors(
                focusedTextColor = colorResource(R.color.black)
            ),
            leadingIcon = {},
            trailingIcon = {}
        )
    }
}