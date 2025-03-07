package com.warrantysafe.app.presentation.ui.screens.main.profileScreen.editProfileScreen

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.domain.utils.Results
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.main.profileScreen.components.DetailRow
import com.warrantysafe.app.presentation.ui.screens.main.profileScreen.components.PhoneDetailRow
import com.warrantysafe.app.presentation.ui.screens.main.utils.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.viewModel.UserViewModel
import com.warrantysafe.app.utils.checkValidNetworkConnection
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditProfileScreen(
    navController: NavController,
    profileImgUri: Uri,
    name: String,
    username: String,
    email: String,
    phoneNumber: String
) {
    val context = LocalContext.current
    var actualName by remember { mutableStateOf(name) }
    var actualUsername by remember { mutableStateOf(username) }
    var actualEmail by remember { mutableStateOf(email) }
    var actualPhoneNumber by remember { mutableStateOf(phoneNumber) }
    val scrollState = rememberScrollState()
    val userViewModel: UserViewModel = koinViewModel()
    // Validation Logic
    val isValidName = actualName.isNotBlank()
    val isValidUsername = actualUsername.matches(Regex("^[a-z0-9.]+$"))
    val isValidEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(actualEmail).matches()
    val isValidPhone = actualPhoneNumber.length in 10..15 && actualPhoneNumber.all { it.isDigit() }

    // State to handle updated profile image
    var updatedProfileUri by remember { mutableStateOf<Uri?>(profileImgUri) }
    val isConnected = remember { mutableStateOf(checkValidNetworkConnection(context)) }

    // Image picker launcher
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            updatedProfileUri = uri// Handle success (uri is not null, content was selected)
        }
    }

    val updateUserState by userViewModel.updateUserState.observeAsState()
    // Handle state changes for user updates
    LaunchedEffect(updateUserState) {
        when (updateUserState) {
            is Results.Loading -> {
                // Show a loading indicator while the product is being updated
                Log.d("ProductUpdate", "Updating profile...")

            }
            is Results.Success -> {
                // Handle the success case when the product has been updated
                val updatedProduct = (updateUserState as Results.Success).data
                Log.d("ProfileUpdate", "Profile updated successfully: ${updatedProduct.username}")
                Toast.makeText(context, "Profile updated successfully!", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }
            is Results.Failure -> {
                // Handle failure state
                val errorMessage = (updateUserState as Results.Failure).exception.message ?: "Unknown error"
                Log.e("ProfileUpdate", "Error updating profile: $errorMessage")
                Toast.makeText(context, "Failed to update profile: $errorMessage", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    Column(modifier = Modifier.fillMaxSize().navigationBarsPadding()) {
        CustomTopAppBar(
            title = {
                Text(
                    text = "Edit Profile",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,  // Handling overflow text
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
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
            actions = {
                IconButton(onClick = {
                    Log.d("AppwriteUpload", "updatedProfileUri ->> ${updatedProfileUri.toString()}")
                    isConnected.value=checkValidNetworkConnection(context)
                    if(!isConnected.value || !isValidPhone || !isValidName || !isValidEmail || !isValidUsername){
                        Toast.makeText(context, "No Valid Details or Check the connection!!", Toast.LENGTH_LONG).show()
                    }else{
                        userViewModel.updateUser(
                            user = User(
                                name = actualName,
                                username = actualUsername,
                                email = actualEmail,
                                phoneNumber = actualPhoneNumber,
                                profileImageUrl = updatedProfileUri.toString()
                            )
                        )
                    }

                }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Check"
                    )
                }
            }
        )
        // Show loading indicator if the product is being updated
        if (updateUserState is Results.Loading) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Text("Profile Updating...", color = Color.Blue)
            }
        }else{
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {

                Spacer(modifier = Modifier.size(16.dp))

                // Profile Avatar Box
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .background(color = colorResource(R.color.black))
                        .align(Alignment.CenterHorizontally)
                        .clickable { launcher.launch("image/*") } // Open gallery on click
                ) {
                    // Display the selected profile image or a placeholder
                    Image(
                        painter = rememberAsyncImagePainter(updatedProfileUri),
                        contentDescription = "Profile Avatar",
                        modifier = Modifier
                            .size(198.dp)
                            .align(Alignment.Center)
                            .clickable { launcher.launch("image/*") } // Open gallery on icon click
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.size(16.dp))



                // Profile details (e.g., name, username, email, phone number)
                DetailRow(
                    "Name",
                    updatedValue = actualName,
                    enable = true,
                    textColor = colorResource(R.color.purple_500),
                    icon = null,
                    onValueChange = { actualName = it }
                )
                if (!isValidName) {
                    Text(
                        text = "Name cannot be empty",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }
                DetailRow(
                    "Username",
                    updatedValue = actualUsername,
                    enable = true,
                    textColor = colorResource(R.color.purple_500),
                    icon = null,
                    onValueChange = { actualUsername = it }
                )
                if (!isValidUsername) {
                    Text(
                        text = "Invalid username! Only lowercase letters, numbers, and dots are allowed.",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }
                DetailRow(
                    "Email",
                    updatedValue = actualEmail,
                    enable = true,
                    textColor = colorResource(R.color.purple_500),
                    icon = null,
                    onValueChange = { actualEmail = it }
                )
                if (!isValidEmail) {
                    Text(
                        text = "Invalid email address",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }
                PhoneDetailRow(
                    label = "Phone",
                    enable = true,
                    phoneNumber = actualPhoneNumber,
                    textColor = colorResource(R.color.purple_500),
                    onCountryCodeChange = { /* Handle country code */ },
                    onPhoneNumberChange = { actualPhoneNumber = it }
                )
                if (!isValidPhone) {
                    Text(
                        text = "Enter a valid phone number (10-15 digits)",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }
            }

        }
    }
}

fun navigateToTab(navController: NavController, route: Route) {
    navController.navigate(route.route) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true // Save state for tabs
        }
        launchSingleTop = true // Avoid multiple instances of the same destination
        restoreState = true // Restore the state if previously saved
    }
}
