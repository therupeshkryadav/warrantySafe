package com.warrantysafe.app.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R

@Composable
fun SearchScreen(navController: NavController) {
    // Content under the TopAppBar
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.xtreme))
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.white)),
            text = "Search results for searched topic"
        )
    }
}

@Preview
@Composable
fun PreviewSearchScreen() {
    SearchScreen(rememberNavController())
}
