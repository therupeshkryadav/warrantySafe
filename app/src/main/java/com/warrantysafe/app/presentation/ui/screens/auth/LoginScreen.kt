package com.warrantysafe.app.presentation.ui.screens.auth

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import com.warrantysafe.app.presentation.viewModel.UserViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(navController: NavController) {

    val userViewModel: UserViewModel = koinViewModel()

    // Observe the sign-up state
    val loginState = userViewModel.loginState.observeAsState()

    val result = loginState.value
    if (result != null) {
        if (result.isSuccess) {
            // Navigate to the next screen
            LaunchedEffect(result.isSuccess) {
                navController.navigate(Route.HomeScreen.route) {
                    popUpTo(Route.LoginScreen.route) { inclusive = true }
                }
            }
        } else if (result.isFailure) {
            val errorMessage = result.exceptionOrNull()?.message ?: "Unknown error"
            Text(text = errorMessage, color = Color.Red)
        }
    }

    // Remember state for user input
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }


    // Form validation check
    val isFormValid = email.value.isNotEmpty() && password.value.isNotEmpty()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
            .statusBarsPadding()
    ) {

        Text(
            text = "Login",
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Username Field
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
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

        Spacer(modifier = Modifier.height(16.dp))

        // Login Button
        Button(
            onClick = {
                if (isFormValid) {
                    userViewModel.loginUser(
                        email = email.value,
                        password = password.value
                    )
                } else {
                    // Show validation errors
                }
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White,
                disabledContainerColor = Color.DarkGray,
                disabledContentColor = Color.White
            ),
            enabled = isFormValid // Disable button if form is invalid
        ) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(Route.SignUpScreen.route) {
                        popUpTo(Route.LoginScreen.route) { inclusive = true }
                    }
                },
            text = "Not have an account ? SignUp",
            fontSize = 12.sp,
            color = Color.Red,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(48.dp))

        // "OR" Section for alternative login options
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "OR",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(48.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
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
