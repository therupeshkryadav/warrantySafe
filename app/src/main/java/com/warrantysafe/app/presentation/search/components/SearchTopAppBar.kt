package com.warrantysafe.app.presentation.search.components

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
fun SearchTopAppBar(
    modifier: Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onBackPress: () -> Unit,
    onSearch: () -> Unit
) {
    androidx.compose.material3.TopAppBar(
        modifier = modifier.padding(end = 10.dp),
        title = {
            androidx.compose.material3.TextField(
                value = query,
                onValueChange = onQueryChange,
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
                    focusedIndicatorColor = colorResource(R.color.xtreme),
                    unfocusedIndicatorColor = colorResource(R.color.xtreme),
                    focusedTextColor = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
                ),
                leadingIcon = {
                    androidx.compose.material3.IconButton(onClick = onBackPress) {
                        androidx.compose.material3.Icon(
                            painter = androidx.compose.ui.res.painterResource(id = R.drawable.arrow_back),
                            contentDescription = "Back",
                            tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                trailingIcon = {
                    androidx.compose.material3.IconButton(onClick = onSearch) {
                        androidx.compose.material3.Icon(
                            painter = androidx.compose.ui.res.painterResource(id = R.drawable.search_warranty),
                            contentDescription = "Search",
                            tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        },
        navigationIcon = {}, // Use `leadingIcon` in TextField for navigation
        actions = {}, // Additional actions can be added here
        colors = androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = androidx.compose.material3.MaterialTheme.colorScheme.surface
        )
    )
}


@Preview(showBackground = true)
@Composable
fun SearchTopAppBarPreview() {
    androidx.compose.material3.MaterialTheme {
        SearchTopAppBar(
            query = "",
            modifier = Modifier,
            onQueryChange = {},
            onBackPress = {},
            onSearch = {}
        )
    }
}
