package com.warrantysafe.app.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.warrantysafe.app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    enabled: Boolean,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    androidx.compose.material3.TextField(
        value = query,
        onValueChange = onQueryChange,
        enabled = enabled,
        placeholder = {
            androidx.compose.material3.Text(
                text = "Search",
                color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
        colors = androidx.compose.material3.TextFieldDefaults.textFieldColors(
            containerColor = androidx.compose.material3.MaterialTheme.colorScheme.surfaceVariant,
            focusedTextColor = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
        ),
        leadingIcon = {
            androidx.compose.material3.IconButton(onClick = onSearch) {
                androidx.compose.material3.Icon(
                    painter = androidx.compose.ui.res.painterResource(id = R.drawable.search_warranty),
                    contentDescription = "Search",
                    tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
                )
            }
        },
        trailingIcon = {}
    )
}

@Preview
@Composable
fun SearchBarPreview(){
    SearchBar(
        query = "",
        enabled = false,
        onQueryChange = {},
        onSearch = {}
    )
}