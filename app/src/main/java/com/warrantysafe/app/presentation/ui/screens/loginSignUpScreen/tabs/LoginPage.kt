package com.warrantysafe.app.presentation.ui.screens.loginSignUpScreen.tabs

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.state.AuthState
import com.warrantysafe.app.presentation.viewModel.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginPage(navController: NavController) {
    val authViewModel: AuthViewModel = koinViewModel()
    val context = LocalContext.current

    // Remember state for user input
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    // Observe authentication state
    val authState by authViewModel.authState.collectAsState()

    // Form validation check
    val isFormValid = username.value.isNotEmpty() && password.value.isNotEmpty()

    // Show loading indicator
    val isLoading = authState is AuthState.Loading

    Column(modifier = Modifier.wrapContentSize()) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Username Field
                TextField(
                    value = username.value,
                    onValueChange = { username.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    placeholder = {
                        Text("Enter Username", color = Color.Gray)
                    },
                    shape = RoundedCornerShape(20.dp),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.username),
                            contentDescription = "Username Icon"
                        )
                    },
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Normal
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password Field
                TextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    placeholder = {
                        Text("Enter Password", color = Color.Gray)
                    },
                    shape = RoundedCornerShape(20.dp),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.password),
                            contentDescription = "Password Icon"
                        )
                    },
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Normal
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )

                // Login Button
                Button(
                    onClick = {
                        if (isFormValid) {
                            authViewModel.login(username.value, password.value)
                        } else {
                            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        }
                    },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    enabled = isFormValid // Disable button if form is invalid
                ) {
                    Text(text = "Login")
                }

                // Show loading indicator when auth is in progress
                if (isLoading) {
                    Spacer(modifier = Modifier.height(16.dp))
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }

                // Show success message after successful login
                if (authState is AuthState.Success) {
                    val successMessage = (authState as AuthState.Success).message
                    Text(
                        text = successMessage,
                        color = Color.Green,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                    )
                    // Optionally, navigate to the next screen
                    LaunchedEffect(successMessage) {
                        navController.navigate(Route.HomeScreen.route)
                    }
                }

                // Show error message if login fails
                if (authState is AuthState.Error) {
                    val errorMessage = (authState as AuthState.Error).errorMessage
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                    )
                }

                // "OR" Section for alternative login options
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 48.dp),
                    textAlign = TextAlign.Center,
                    text = "OR",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                        .clickable {
                            // Handle alternative login (e.g., phone number login)
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth()
                            .border(width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(20.dp)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.phone_pp), // Replace with your phone icon resource
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            modifier = Modifier.padding(8.dp),
                            textAlign = TextAlign.Center,
                            text = "Continue with Phone Number",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLoginPage() {
    LoginPage(rememberNavController())
}
