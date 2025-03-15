package com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.settingsScreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.domain.utils.Results
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.main.homeScreen.navigateToTab
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.settingsScreen.components.SettingsItem
import com.warrantysafe.app.presentation.ui.screens.main.utils.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.viewModel.UserViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(navController: NavController) {
    val userViewModel: UserViewModel = koinViewModel()
    var showChangePasswordDialog by remember { mutableStateOf(false) }
    var showDeleteAccountDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val settingsList = listOf(
        "Change Password",
        "Delete Account"
    )

    fun onClicked(item: String) {
        when (item) {
            "Change Password" -> showChangePasswordDialog = true
            "Delete Account" -> showDeleteAccountDialog = true
        }
    }

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
                    modifier = Modifier.fillMaxWidth()
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
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
                SettingsItem(settingsText = setting, onClick = { onClicked(setting) })
                Spacer(modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color.White))
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
        }

        if (showChangePasswordDialog) {
            ChangePasswordDialog(onDismiss = { showChangePasswordDialog = false })
        }
        if (showDeleteAccountDialog) {
            DeleteAccountDialog(userViewModel,navController, onDismiss = { showDeleteAccountDialog = false })
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

@Composable
fun DeleteAccountDialog(
    userViewModel: UserViewModel,
    navController: NavController,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    val deleteUserState by userViewModel.deleteUserState.collectAsState()

    // Handle state changes for delete user
    LaunchedEffect(deleteUserState) {
        when (deleteUserState) {
            is Results.Success -> {
                Toast.makeText(context, "Account deleted successfully", Toast.LENGTH_SHORT).show()
                navController.navigate(Route.LoginScreen.route) {
                    popUpTo(Route.SettingsScreen.route) { inclusive = true }
                }
            }
            is Results.Failure -> {
                val errorMessage = (deleteUserState as Results.Failure).exception.message
                Log.d("DeleteState", errorMessage.toString())
                Toast.makeText(context, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete Account", fontWeight = FontWeight.Bold) },
        text = {
            Column {
                Text("Enter your password to confirm account deletion.")

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        showError = false // Reset error on input change
                    },
                    label = { Text("Enter Password") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isError = showError,
                    modifier = Modifier.fillMaxWidth()
                )

                if (showError) {
                    Text(
                        text = "Incorrect password. Try again.",
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (password.isNotEmpty()) {
                        userViewModel.deleteUser(password)
                    } else {
                        showError = true
                    }
                },
                enabled = password.isNotEmpty()
            ) {
                Text("Delete", color = Color.Red)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )

    // Show Loading Indicator
    if (deleteUserState is Results.Loading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        ) {
            CircularProgressIndicator()
        }
    }
}




@Preview(showBackground = true)
@Composable
private fun PreviewSettingsScreen() {
    SettingsScreen(rememberNavController())
}
