package com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.settingsScreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.settingsScreen.components.SettingsItem
import com.warrantysafe.app.presentation.ui.screens.main.utils.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.viewModel.UserViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(navController: NavController) {
    val userViewModel: UserViewModel = koinViewModel()
    var showChangePasswordDialog by remember { mutableStateOf(false) }
    var showDeleteAccountDialog by remember { mutableStateOf(false) }

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
            ChangePasswordDialog(userViewModel,onDismiss = { showChangePasswordDialog = false })
        }
        if (showDeleteAccountDialog) {
            DeleteAccountDialog(userViewModel,navController, onDismiss = { showDeleteAccountDialog = false })
        }
    }
}

@Composable
fun ChangePasswordDialog(
    userViewModel: UserViewModel,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    val changePasswordState by userViewModel.changePasswordState.collectAsState()

    LaunchedEffect(changePasswordState) {
        when (changePasswordState) {
            is Results.Success -> {
                Toast.makeText(context, "Password changed successfully", Toast.LENGTH_SHORT).show()
                onDismiss()
            }
            is Results.Failure -> {
                val errorMessage = (changePasswordState as Results.Failure).exception.message
                Toast.makeText(context, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    AlertDialog(
        containerColor = Color.White,
        onDismissRequest = onDismiss,
        title = { Text("Change Password", fontWeight = FontWeight.Bold) },
        text = {
            Column {
                OutlinedTextField(
                    value = currentPassword,
                    onValueChange = { currentPassword = it },
                    label = { Text("Current Password") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isError = showError,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    label = { Text("New Password") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isError = showError,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm New Password") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isError = showError,
                    modifier = Modifier.fillMaxWidth()
                )

                if (showError) {
                    Text(
                        text = "Passwords do not match.",
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
                    if (newPassword == confirmPassword && newPassword.isNotEmpty()) {
                        userViewModel.changePassword(currentPassword, newPassword)
                    } else {
                        showError = true
                    }
                }
            ) {
                Text("Change", color = Color.Blue)
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
        containerColor = Color.White,
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
