package com.warrantysafe.app.presentation.profile.edit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
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
import com.warrantysafe.app.presentation.profile.components.DetailRow

@Composable
fun EditProfileScreen(
    navController: NavController,
    fullName: String,
    userName: String,
    emailId: String,
    phoneNumber: String
) {
    var scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 8.dp, end = 8.dp)
            .verticalScroll(scrollState)
    ) {

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
            fullName,
            enable = true,
            textColor = colorResource(R.color.purple_500),
            icon = null,
            borderColor = colorResource(R.color.black)
        )
        DetailRow(
            "Username",
            userName,
            enable = true,
            textColor = colorResource(R.color.purple_500),
            icon = null,
            borderColor = colorResource(R.color.black)
        )
        DetailRow(
            "Email",
            emailId,
            enable = true,
            textColor = colorResource(R.color.purple_500),
            icon = null,
            borderColor = colorResource(R.color.black)
        )
        DetailRow(
            "Phone",
            phoneNumber,
            enable = true,
            textColor = colorResource(R.color.purple_500),
            icon = null,
            borderColor = colorResource(R.color.black)
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