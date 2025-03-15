package com.warrantysafe.app.presentation.ui.screens.auth

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.firestore.FirebaseFirestore
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.domain.utils.Results
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.main.utils.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.viewModel.UserViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(
    navController: NavController
) {
    val userViewModel: UserViewModel = koinViewModel()
    val context = LocalContext.current

    // Observe the sign-up state
    val signUpState = userViewModel.signUpState.observeAsState()
    val isLoading = userViewModel.isLoading.observeAsState(initial = false)
    // Handle login state
    when (val result = signUpState.value) {
        is Results.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate(Route.HomeScreen.route) {
                    popUpTo(Route.SignUpScreen.route) { inclusive = true }
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
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confPassword = remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confPasswordVisible by remember { mutableStateOf(false) }
    // State for storing the error message
    var phoneError by remember { mutableStateOf<String?>(null) }
    // Email Address Field
    var isEmailValid by remember { mutableStateOf(true) }
    var emailExistenceMessage by remember { mutableStateOf<String?>(null) }

    var passwordError by remember { mutableStateOf<String?>(null) }

    // Default profile image URI (when no image is selected)
    val defaultProfileImage =
        "android.resource://com.warrantysafe.app/drawable/profile_placeholder".toUri()

    // State to handle profile image
    var profileImageUri by remember { mutableStateOf<Uri?>(defaultProfileImage) }

    // Image picker launcher
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        profileImageUri = uri ?: defaultProfileImage
    }

    val isValidInput = name.value.isNotEmpty() && email.value.isNotEmpty() && phoneNumber.value.isNotEmpty() && password.value.isNotEmpty() && confPassword.value == password.value && phoneError.isNullOrEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .systemBarsPadding()
            .background(color = Color.White)
    ) {
        CustomTopAppBar(
            title = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Sign Up",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        navController.navigate(Route.LoginScreen.route) {
                            popUpTo(Route.SignUpScreen.route) { inclusive = true }
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Back"
                    )
                }
            },
            actions = {}
        )
        Column(Modifier.fillMaxSize() .verticalScroll(rememberScrollState())){
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .clip(CircleShape)
                    .fillMaxWidth()
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
            // fullName Field
            TextField(
                value = name.value,
                onValueChange = { name.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(20.dp)
                    ),
                placeholder = {
                    Text("Enter Your Full Name", color = Color.Gray)
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

            // Email validation regex
            fun isValidEmail(email: String): Boolean {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }

            LaunchedEffect(email.value) {
                if (email.value.isNotEmpty() && isValidEmail(email.value)) {
                    checkEmailIdExists(email.value) { exists ->
                        emailExistenceMessage = if (exists) "Email ID is registered with us!!" else null
                    }
                } else {
                    emailExistenceMessage = null
                }
            }

            TextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                    isEmailValid = isValidEmail(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .border(
                        width = 1.dp,
                        color = if (isEmailValid) Color.LightGray else Color.Red,
                        shape = RoundedCornerShape(20.dp)
                    ),
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

            // Error messages
            if (email.value.isNotEmpty()) {
                if (!isEmailValid) {
                    Text(
                        text = "Incorrect email address format!!",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                } else if (emailExistenceMessage != null) {
                    Text(
                        text = emailExistenceMessage!!,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }
            }


            Spacer(modifier = Modifier.height(16.dp))



            // Phone Number Field
            TextField(
                value = phoneNumber.value,
                onValueChange = {
                    if (it.all { char -> char.isDigit() }) { // Check if all characters are digits
                        phoneNumber.value = it
                        phoneError = if (phoneNumber.value.isEmpty() || it.length in 10..15) null else "Enter a valid phone number (10-15 digits)"
                        // Check in database
                        checkPhoneNumberExists(it) { exists ->
                            if (exists) {
                                phoneError = "Number is registered with us!!"
                            }
                        }
                    } else {
                        phoneError = "Only numbers are allowed!"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .border(
                        width = 1.dp,
                        color = if (phoneError != null) Color.Red else Color.LightGray,
                        shape = RoundedCornerShape(20.dp)
                    ),
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

           // Show Error Message if Validation Fails
            phoneError?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 20.dp, top = 4.dp).fillMaxWidth()
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            // Function to validate password
            fun isValidPassword(input: String): Boolean {
                val passwordRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@\$!%*?&])[A-Za-z0-9@\$!%*?&]{8,}$".toRegex()
                // Contains at least one UpperCase, contains only allowed characters
                return input.matches(passwordRegex) &&
                        input.any { it.isDigit() } && // At least one digit
                        input.contains("@") // At least one '@'
            }

            // Password Field
            TextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                    passwordError = if (password.value.isEmpty() || isValidPassword(it)) null else "Password must contain an uppercase letter, contain at least one digit, one '@', and no other special characters."
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .border(
                        width = 1.dp,
                        color = if (passwordError != null) Color.Red else Color.LightGray,
                        shape = RoundedCornerShape(20.dp)
                    ),
                placeholder = {
                    Text("Enter Your Password", color = Color.Gray)
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.password),
                        contentDescription = "Password Icon"
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = if(passwordVisible)painterResource(R.drawable.ic_eye)else painterResource(R.drawable.unhide_password),
                            contentDescription = "Toggle Password Visibility"
                        )
                    }
                },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            // Show error message if validation fails
            passwordError?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }


            Spacer(modifier = Modifier.height(16.dp))
            var confPasswordError by remember { mutableStateOf<String?>(null) }
            // Password Field with Visibility Toggle
            TextField(
                value = confPassword.value,
                onValueChange = {
                    confPassword.value = it
                    confPasswordError = if (confPassword.value.isEmpty() || password.value == it) null else "Password does not matches, Check your password!!"
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .border(width = 1.dp,
                        color = if (confPasswordError != null) Color.Red else Color.LightGray,
                        shape = RoundedCornerShape(20.dp)),
                placeholder = {
                    Text("Confirm Your Password", color = Color.Gray)
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.password),
                        contentDescription = "Password Icon"
                    )
                },
                singleLine = true,
                visualTransformation = if (confPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { confPasswordVisible = !confPasswordVisible }) {
                        Icon(
                            painter = if(confPasswordVisible)painterResource(R.drawable.ic_eye)else painterResource(R.drawable.unhide_password),
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

            // Show error message if validation fails
            confPasswordError?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // SignUp Button
            Button(
                onClick = {
                    if (isValidInput) {
                        userViewModel.signUpUser(
                            User(
                                name = name.value,
                                email = email.value,
                                phoneNumber = phoneNumber.value,
                                profileImageUrl = profileImageUri.toString(),
                                password = password.value
                            )
                        )
                    } else {
                        Toast.makeText(context, "Please validate the fields ", Toast.LENGTH_SHORT)
                            .show()// Show validation errors
                    }
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp),
                enabled = isValidInput, // Disable the button while loading
            ) {
                if (isLoading.value) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
                } else {
                    Text("Sign Up")
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
                        navController.navigate(Route.LoginScreen.route) {
                            popUpTo(Route.SignUpScreen.route) { inclusive = true }
                        }
                    },
                text = "Already have an account ? Login",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )
        }


    }
}

// Function to check if the phone number exists in Firestore
fun checkPhoneNumberExists(phone: String, onResult: (Boolean) -> Unit) {
    val usersCollection = FirebaseFirestore.getInstance().collection("users")
    usersCollection.whereEqualTo("phoneNumber", phone)
        .get()
        .addOnSuccessListener { documents ->
            onResult(!documents.isEmpty) // If documents are found, phone exists
        }
        .addOnFailureListener {
            onResult(false) // Assume not found on failure
        }
}

// Function to check if the phone number exists in Firestore
fun checkEmailIdExists(email: String, onResult: (Boolean) -> Unit) {
    val usersCollection = FirebaseFirestore.getInstance().collection("users")
    usersCollection.whereEqualTo("email", email)
        .get()
        .addOnSuccessListener { documents ->
            onResult(!documents.isEmpty) // If documents are found, phone exists
        }
        .addOnFailureListener {
            onResult(false) // Assume not found on failure
        }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpPage() {
    SignUpScreen(navController = rememberNavController()) // You can use a mock NavController here
}
