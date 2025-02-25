package com.warrantysafe.app.presentation.ui.screens.auth

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.warrantysafe.app.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SignUpScreens() {
    val context = LocalContext.current

    val username = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val defaultProfileImage = Uri.parse("android.resource://com.warrantysafe.app/drawable/profile_placeholder")
    var profileImageUri by remember { mutableStateOf<Uri?>(defaultProfileImage) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        profileImageUri = uri ?: defaultProfileImage
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        IconButton(onClick = { /* Handle back navigation */ }) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "Back"
            )
        }

        Text(
            text = "Sign Up",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(width = 1.dp, Color.Black, CircleShape)
                .align(Alignment.CenterHorizontally)
                .clickable { launcher.launch("image/*") },
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(profileImageUri),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(value = name, label = "Full Name")
        CustomTextField(value = username, label = "Username")
        CustomTextField(value = email, label = "Email Address")
        CustomTextField(value = phoneNumber, label = "Phone Number")

        var isUsernameValid by remember { mutableStateOf(true) }
        var isCheckingAvailability by remember { mutableStateOf(false) }
        var isUsernameTaken by remember { mutableStateOf(true) }

        fun isValidUsername(username: String): Boolean {
            return username.matches(Regex("^[a-z0-9.]+$"))
        }

        // Simulated function to check username availability
        fun checkUsernameAvailability(username: String) {
            if (username.isEmpty() || !isValidUsername(username)) {
                isUsernameTaken = false
                isCheckingAvailability = false
                return
            }

            isCheckingAvailability = true
            // Simulate a network request (Replace with actual API/Firebase call)
            CoroutineScope(Dispatchers.IO).launch {
                delay(1000) // Simulating network delay
                isUsernameTaken = username == "existinguser" // Replace with real check
                isCheckingAvailability = false
            }
        }

        TextField(
            value = username.value,
            onValueChange = { input ->
                val lowercasedInput = input.lowercase()
                username.value = lowercasedInput
                isUsernameValid = isValidUsername(lowercasedInput)

                // Call function to check username availability
                checkUsernameAvailability(lowercasedInput)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .border(
                    width = 1.dp,
                    color = if (username.value.isEmpty() || (isUsernameValid && !isUsernameTaken)) Color.LightGray else Color.Red,
                    shape = RoundedCornerShape(20.dp)
                ),
            placeholder = {
                Text("Choose Your Username", color = Color.Gray)
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.username_pp),
                    contentDescription = "Username Icon"
                )
            },
            trailingIcon = {
                when {
                    isCheckingAvailability -> CircularProgressIndicator(Modifier.size(20.dp))
                    isUsernameTaken -> Icon(
                        imageVector = Icons.Filled.Warning,
                        contentDescription = "Username Taken",
                        tint = Color.Red
                    )
                    else -> null
                }
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

// Error messages
        if (username.value.isNotEmpty() && !isUsernameValid) {
            Text(
                text = "Invalid username! Only lowercase letters, numbers, and dots are allowed.",
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }

        if (username.value.isNotEmpty() && isUsernameTaken) {
            Text(
                text = "This username is already taken. Try another one.",
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }



        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Handle sign-up action */ },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sign Up")
        }
    }
}

@Composable
fun CustomTextField(value: MutableState<String>, label: String) {
    TextField(
        value = value.value,
        onValueChange = { value.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.LightGray, RoundedCornerShape(20.dp)),
        placeholder = { Text("Enter $label", color = Color.Gray) },
        singleLine = true,
        textStyle = TextStyle(fontSize = 16.sp, color = Color.Black)
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen() {
    SignUpScreens()
}
