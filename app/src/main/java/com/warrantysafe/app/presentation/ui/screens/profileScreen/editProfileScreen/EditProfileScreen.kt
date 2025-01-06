package com.warrantysafe.app.presentation.ui.screens.profileScreen.editProfileScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.utils.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.ui.screens.profileScreen.components.DetailRow
import com.warrantysafe.app.presentation.ui.screens.profileScreen.components.PhoneDetailRow

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
    var actualCountryCode by remember { mutableStateOf("+91") } // Default country code
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp)
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
                    onClick = {navController.popBackStack()}
                ){
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            actions = {
                IconButton(onClick = { navigateToTab(navController,Route.ProfileScreen) }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Check"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.size(16.dp))
        // Profile Avatar
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .clickable {
                    //Edit Profile Image
                }
                .background(color = colorResource(R.color.black))
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(R.drawable.profile_avatar),
                modifier = Modifier
                    .size(198.dp)
                    .align(Alignment.Center)
                    .clip(CircleShape),
                contentDescription = "Profile Avatar",
                contentScale = ContentScale.Crop
            )
        }
        // Profile Details
        DetailRow(
            "Name",
            updatedValue = actualFullName,
            enable = true,
            textColor = colorResource(R.color.purple_500),
            borderColor = colorResource(R.color.black),
            icon = null,
            onValueChange = { actualFullName = it }
        )
        DetailRow(
            "Username",
            updatedValue = actualUsername,
            enable = true,
            textColor = colorResource(R.color.purple_500),
            borderColor = colorResource(R.color.black),
            icon = null,
            onValueChange = { actualUsername = it }
        )
        DetailRow(
            "Email",
            updatedValue = actualEmailId,
            enable = true,
            textColor = colorResource(R.color.purple_500),
            borderColor = colorResource(R.color.black),
            icon = null,
            onValueChange = { actualEmailId = it }
        )
        // Phone Number DetailRow with Country Code Picker
        PhoneDetailRow(
            label = "Phone",
            enable = true,
            phoneNumber = actualPhoneNumber,
            countryCode = actualCountryCode,
            textColor = colorResource(R.color.purple_500),
            borderColor = colorResource(R.color.black),
            onCountryCodeChange = { actualCountryCode = it },
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

@Preview(showBackground = true)
@Composable
private fun PrevEditProfile() {
    EditProfileScreen(
        navController = rememberNavController(),
        fullName = "Rupesh Kumar Yadav",
        userName = "therupeshkryadav",
        emailId = "rupesh.official484@gmail.com",
        phoneNumber = "7233966649"
    )
}