package com.warrantysafe.app.presentation.ui.profile.edit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.ui.profile.components.DetailRow
import com.warrantysafe.app.presentation.ui.profile.components.PhoneDetailRow

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
    var scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp)
            .verticalScroll(scrollState)
    ) {
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