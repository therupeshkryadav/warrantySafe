package com.warrantysafe.app.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun SearchScreen(navController: NavController) {
    // Content under the TopAppBar
    Column( modifier = Modifier
        .fillMaxSize()
        .padding(all = 5.dp)
        .statusBarsPadding()) {
        Text(
            modifier = Modifier.padding(horizontal = 5.dp),
            text = "Search results for searched topic"
        )
    }
}

@Preview
@Composable
fun PreviewSearchScreen() {
    SearchScreen(rememberNavController())
}
