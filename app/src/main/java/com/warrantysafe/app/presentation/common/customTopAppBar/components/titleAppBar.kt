package com.warrantysafe.app.presentation.common.customTopAppBar.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.navgraph.Route

@SuppressLint("ComposableNaming")
@Composable
fun titleAppBar(currentRoute: String) {
    // Define route-to-title mapping
    val titleConfig = mapOf(
        Route.HomeScreen.route to TitleConfig.Title(""), // Home screen with empty title
        Route.ProfileScreen.route to TitleConfig.Title(""), // Profile screen
        Route.AddScreen.route to TitleConfig.Title("Add Warranty"), // Add Warranty screen
        Route.SearchScreen.route to TitleConfig.SearchBar // Search screen with a search bar
    )

    // Determine the title configuration based on the route
    val titleConfigForRoute = titleConfig[currentRoute] ?: TitleConfig.Title("Unknown Screen")

    // Render the appropriate UI
    when (titleConfigForRoute) {
        is TitleConfig.Title -> {
            Text(
                text = titleConfigForRoute.text,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        is TitleConfig.SearchBar -> {
            var text by remember { mutableStateOf("") }
            TextField(
                value = text,
                onValueChange = { text = it },
                placeholder = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(1f)
                            .background(color = colorResource(R.color.transparent)),
                        text = "Search",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(62.dp)
                    .background(color = colorResource(R.color.transparent)),
                textStyle = MaterialTheme.typography.titleLarge,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = colorResource(R.color.black),
                    unfocusedTextColor = colorResource(R.color.black),
                    focusedContainerColor = colorResource(R.color.transparent),
                    unfocusedContainerColor = colorResource(R.color.transparent),
                    focusedIndicatorColor = Color.Transparent, // Removes the bottom line when focused
                    unfocusedIndicatorColor = Color.Transparent // Removes the bottom line when unfocused
                )
            )
        }
    }
}

// Configuration sealed class for dynamic title management
sealed class TitleConfig {
    data class Title(val text: String) : TitleConfig()
    object SearchBar : TitleConfig()
}

@Composable
fun TitleAppBarPreview() {
    Column {
        // Preview for Home Screen
        titleAppBar(currentRoute = Route.HomeScreen.route)

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(color = colorResource(R.color.black))
        )

        // Preview for Profile Screen
        titleAppBar(currentRoute = Route.ProfileScreen.route)

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(color = colorResource(R.color.black))
        )

        // Preview for Add Warranty Screen
        titleAppBar(currentRoute = Route.AddScreen.route)

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(color = colorResource(R.color.black))
        )

        // Preview for Search Screen
        titleAppBar(currentRoute = Route.SearchScreen.route)

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(color = colorResource(R.color.black))
        )

        // Preview for Unknown Route
        titleAppBar(currentRoute = "unknown_route")
    }
}

@Preview(name = "TitleAppBar Previews")
@Composable
fun PreviewTitleAppBar() {
    TitleAppBarPreview()
}
