package com.warrantysafe.app.presentation.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.warrantysafe.app.domain.utils.Results
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.viewModel.UserViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(navController: NavController) {

    val userViewModel: UserViewModel = koinViewModel()
    val context = LocalContext.current

    // Observe the login state
    val loginState = userViewModel.loginState.observeAsState()
    val isLoading = userViewModel.isLoading.observeAsState(initial = false)

    // Handle login state
    when (val result = loginState.value) {
        is Results.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate(Route.HomeScreen.route) {
                    popUpTo(Route.LoginScreen.route) { inclusive = true }
                }
            }
        }

        is Results.Failure -> {
            val errorMessage = result.exception.message ?: "No account found with these credentials"
            Toast.makeText(context,errorMessage, Toast.LENGTH_SHORT).show()
        }

        else -> { /* No-op for initial or null state */ }
    }

    // Remember state for user input
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .systemBarsPadding()
            .background(color = Color.White)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            alignment = Alignment.Center,
            painter = painterResource(R.drawable.warranty_logo),
            contentDescription = null
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Log in",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Email Field
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(20.dp)
                ),
            placeholder = {
                Text("Enter Email", color = Color.Gray)
            },
            shape = RoundedCornerShape(20.dp),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.email_pp),
                    contentDescription = "Email Icon"
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
                .padding(horizontal = 16.dp)
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

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null // Disables ripple effect
                ) {

                },
            text = "Forgot Password ?",
            fontSize = 12.sp,
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )

        // Login Button
        Button(
            onClick = {
                if (email.value.isNotEmpty() && password.value.isNotEmpty()) {
                    userViewModel.loginUser(
                        email = email.value,
                        password = password.value
                    )
                } else {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT)
                        .show()// Show validation errors
                }
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White,
                disabledContainerColor = Color.DarkGray,
                disabledContentColor = Color.White
            ),
            enabled = !isLoading.value, // Disable the button while loading
        ) {
            if (isLoading.value) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
            } else {
                Text("Login")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null // Disables ripple effect
                ) {
                    navController.navigate(Route.SignUpScreen.route) {
                        popUpTo(Route.LoginScreen.route) { inclusive = true }
                    }
                },
            text = "Not have an account ? SignUp",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(48.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(20.dp)),
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

@Preview(showBackground = true)
@Composable
private fun PreviewLoginPage() {
    LoginScreen(rememberNavController())
}
