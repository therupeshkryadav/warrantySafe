package com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.settingsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.settingsScreen.components.SettingsItem
import com.warrantysafe.app.presentation.ui.screens.main.utils.customTopAppBar.CustomTopAppBar

@Composable
fun SettingsScreen(navController: NavController) {
    var showChangePasswordDialog by remember { mutableStateOf(false) }

    val settingsList = listOf("Change Password")

    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopAppBar(
            title = {
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth().wrapContentHeight()
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            actions = {}
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(settingsList) { setting ->
                SettingsItem(settingsText = setting, onClick = {
                    if (setting == "Change Password") {
                        showChangePasswordDialog = true
                    }
                })
                Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(Color.White))
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        if (showChangePasswordDialog) {
            ChangePasswordDialog(onDismiss = { showChangePasswordDialog = false })
        }
    }
}

@Composable
fun ChangePasswordDialog(onDismiss: () -> Unit) {
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        title = { Text("Change Password", fontWeight = FontWeight.Bold) },
        text = {
            Column {
                OutlinedTextField(
                    value = currentPassword,
                    onValueChange = { currentPassword = it },
                    label = { Text("Current Password") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    label = { Text("New Password") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm New Password") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))

                errorMessage?.let {
                    Text(text = it, color = Color.Red, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (newPassword != confirmPassword) {
                    errorMessage = "Passwords do not match"
                } else if (newPassword.isEmpty() || currentPassword.isEmpty()) {
                    errorMessage = "All fields are required"
                } else {
                    // TODO: Implement password change logic
                    onDismiss()
                }
            }) {
                Text("Change")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewSettingsScreen() {
    SettingsScreen(rememberNavController())
}
