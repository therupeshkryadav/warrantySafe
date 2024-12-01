package com.warrantysafe.app.presentation.notify

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallTopAppBarExample() {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Small Top App Bar") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        },
        content = { innerPadding ->
            // Your screen content goes here
            Text(
                text = "Hello, this is the content area!",
                modifier = androidx.compose.ui.Modifier.padding(innerPadding)
            )
        }
    )
}

@Preview
@Composable
fun SmallTopAppBarExamplePreview() {
    MaterialTheme {
        SmallTopAppBarExample()
    }
}
