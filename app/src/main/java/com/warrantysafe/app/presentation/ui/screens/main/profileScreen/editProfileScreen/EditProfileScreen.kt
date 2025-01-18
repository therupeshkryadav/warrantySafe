package com.warrantysafe.app.presentation.ui.screens.main.profileScreen.editProfileScreen

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.main.profileScreen.components.DetailRow
import com.warrantysafe.app.presentation.ui.screens.main.profileScreen.components.PhoneDetailRow
import com.warrantysafe.app.presentation.ui.screens.main.utils.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.viewModel.UserViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditProfileScreen(
    navController: NavController,
    fullName: String,
    userName: String,
    emailId: String,
    phoneNumber: String
) {
    var actualFullName by remember { mutableStateOf(fullName) }
    var actualUsername by remember { mutableStateOf(userName) }
    var actualEmailId by remember { mutableStateOf(emailId) }
    var actualPhoneNumber by remember { mutableStateOf(phoneNumber) }
    val scrollState = rememberScrollState()
    val userViewModel: UserViewModel = koinViewModel()

    // State to handle profile image
    var profileImageUri by remember { mutableStateOf<Uri?>(null) }

    // Image picker launcher
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            profileImageUri = uri // Update the profile image URI // Handle success (uri is not null, content was selected)
        } else {
           profileImageUri = null // Handle cancellation (uri is null, no content selected)
        }
    }

    val imageUri = profileImageUri?: Uri.parse("android.resource://com.warrantysafe.app/${R.drawable.profile_placeholder}")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
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
                    Log.d("Dagger","$imageUri")
                    navigateToTab(navController, Route.ProfileScreen) }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Check"
                    )
                }
            }
        )
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
            if (profileImageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(imageUri),
                    contentDescription = "Profile Avatar",
                    modifier = Modifier
                        .size(198.dp)
                        .align(Alignment.Center)
                        .clickable { launcher.launch("image/*") } // Open gallery on icon click
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

            } else {
                Image(
                    painter = painterResource(R.drawable.profile_placeholder),
                    contentDescription = "Profile Placeholder",
                    modifier = Modifier
                        .size(198.dp)
                        .align(Alignment.Center)
                        .clip(CircleShape)
                        .clickable { launcher.launch("image/*") },// Open gallery on icon click,
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.size(16.dp))

        // Profile details (e.g., name, username, email, phone number)
        DetailRow(
            "Name",
            updatedValue = actualFullName,
            enable = true,
            textColor = colorResource(R.color.purple_500),
            icon = null,
            onValueChange = { actualFullName = it }
        )
        DetailRow(
            "Username",
            updatedValue = actualUsername,
            enable = true,
            textColor = colorResource(R.color.purple_500),
            icon = null,
            onValueChange = { actualUsername = it }
        )
        DetailRow(
            "Email",
            updatedValue = actualEmailId,
            enable = true,
            textColor = colorResource(R.color.purple_500),
            icon = null,
            onValueChange = { actualEmailId = it }
        )
        PhoneDetailRow(
            label = "Phone",
            enable = true,
            phoneNumber = actualPhoneNumber,
            textColor = colorResource(R.color.purple_500),
            onCountryCodeChange = { /* Handle country code */ },
            onPhoneNumberChange = { actualPhoneNumber = it }
        )
    }
}

private fun navigateToTab(navController: NavController, route: Route) {
    navController.navigate(route.route) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true // Save state for tabs
        }
        launchSingleTop = true // Avoid multiple instances of the same destination
        restoreState = true // Restore the state if previously saved
    }
}
