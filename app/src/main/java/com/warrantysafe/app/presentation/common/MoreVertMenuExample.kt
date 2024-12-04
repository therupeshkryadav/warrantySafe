package com.warrantysafe.app.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.presentation.common.dropDownMenu.dropDownMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreVertMenuExample(navController: NavController) {
    // State to manage the visibility of the dropdown menu
    var isMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("More Vert Menu Example") },
                actions = {
                    // More Vert Icon Button
                    IconButton(onClick = { isMenuExpanded = !isMenuExpanded }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "More Options"
                        )
                    }

                    // Custom Dropdown Menu
                    dropDownMenu(
                        menuItems =  listOf("Home", "Profile", "Settings", "Logout"),
                        isExpanded =  remember { mutableStateOf(isMenuExpanded) },
                        onItemClicked = { selectedItem ->
                            // Handle menu item clicks here
                            when (selectedItem) {
                                "Home" -> println("Navigating to Home")
                                "Profile" -> println("Navigating to Profile")
                                "Settings" -> println("Navigating to Settings")
                                "Logout" -> println("Logging out")
                            }
                        }
                    )
                }
            )
        }
    ) { paddingValues ->
        // Main content of the screen
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Welcome to the More Vert Menu Example",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoreVertMenuExamplePreview() {
    MoreVertMenuExample(navController = rememberNavController())
}
