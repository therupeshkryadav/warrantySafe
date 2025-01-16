package com.warrantysafe.app.presentation.ui.screens.loginSignUpScreen.tabs

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.state.AuthState
import com.warrantysafe.app.presentation.viewModel.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpPage(
    navController: NavController
) {
    val authViewModel: AuthViewModel = koinViewModel()
    val context = LocalContext.current
    val authState by authViewModel.authState.collectAsState()

    // Remember state for user input
    val username = remember { mutableStateOf("") }
    val fullName = remember { mutableStateOf("") }
    val emailAddress = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // State to handle profile image
    var profileImageUri by remember { mutableStateOf<Uri?>(null) }

    // Image picker launcher
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> profileImageUri = uri }

    val imageUri = profileImageUri ?: Uri.parse("android.resource://com.warrantysafe.app/${R.drawable.profile_placeholder}")

    Column(modifier = Modifier.padding(8.dp).wrapContentSize()) {
        // Profile Avatar
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(color = colorResource(R.color.black))
                .align(Alignment.CenterHorizontally)
                .clickable { launcher.launch("image/*") }
        ) {
            Image(
                painter = rememberAsyncImagePainter(imageUri),
                modifier = Modifier
                    .size(198.dp)
                    .align(Alignment.Center)
                    .clip(CircleShape),
                contentDescription = "Profile Avatar",
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // FullName Field
        TextField(
            value = fullName.value,
            onValueChange = { fullName.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(20.dp)),
            placeholder = {
                Text("Enter Your Full Name", color = Color.Gray)
            },
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

        // Username Field
        TextField(
            value = username.value,
            onValueChange = { username.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(20.dp)),
            placeholder = {
                Text("Choose Your username", color = Color.Gray)
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.username_pp),
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

        // Email Address Field
        TextField(
            value = emailAddress.value,
            onValueChange = { emailAddress.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(20.dp)),
            placeholder = {
                Text("Enter Your Email Address", color = Color.Gray)
            },
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

        // Phone Number Field
        TextField(
            value = phoneNumber.value,
            onValueChange = { phoneNumber.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(20.dp)),
            placeholder = {
                Text("Enter Your Phone Number", color = Color.Gray)
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.phone_pp),
                    contentDescription = "Phone Number Icon"
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

        // Password Field with Visibility Toggle
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(20.dp)),
            placeholder = {
                Text("Enter Your Password", color = Color.Gray)
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.password),
                    contentDescription = "Password Icon"
                )
            },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Toggle Password Visibility"
                    )
                }
            },
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

        // SignUp Button
        Button(
            onClick = {
                // Perform validation and sign up logic
                if (username.value.isEmpty() || fullName.value.isEmpty() || emailAddress.value.isEmpty() || phoneNumber.value.isEmpty() || password.value.isEmpty()) {
                    Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                } else {
                    authViewModel.signup(
                        imageUri,
                        username.value,
                        emailAddress.value,
                        phoneNumber.value,
                        password.value
                    )
                }
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Sign Up")
        }

        // Observing AuthState to show appropriate feedback
        when (authState) {
            is AuthState.Loading -> {
                // Show loading indicator
            }
            is AuthState.Success -> {
                // Navigate to next screen or show success message
                Toast.makeText(context, "Sign up successful", Toast.LENGTH_SHORT).show()
                navController.navigate(Route.HomeScreen.route) // Navigate to login page
            }
            is AuthState.Error -> {
                val errorMessage = (authState as AuthState.Error).errorMessage
                Toast.makeText(context, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
            }
            else -> { /* Do nothing */ }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpPage() {
    SignUpPage(navController = rememberNavController()) // You can use a mock NavController here
}