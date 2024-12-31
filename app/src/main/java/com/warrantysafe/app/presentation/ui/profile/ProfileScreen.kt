package com.warrantysafe.app.presentation.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.ui.navgraph.Route
import com.warrantysafe.app.presentation.ui.profile.components.DetailRow


@Composable
fun ProfileScreen(navController: NavController) {
    val fullName = "Rupesh Kumar Yadav"
    val username = "therupeshkryadav"
    val email = "rupesh.official484@gmail.com"
    val phoneNumber = "7233966649"

    var scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .verticalScroll(scrollState)
    ) {
        // Edit Profile Icon
        Box(modifier = Modifier.fillMaxWidth()) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterEnd)
                    .clickable { navigateToEditProfile(
                        navController = navController,
                        fullName = fullName,
                        userName = username,
                        emailId = email,
                        phone = phoneNumber,
                    ) },
                painter = painterResource(R.drawable.edit),
                contentDescription = "Edit Profile"
            )
        }

        // Profile Avatar
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
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
            updatedValue = fullName,
            enable = false,
            textColor = colorResource(R.color.purple_500),
            borderColor = colorResource(R.color.black),
            icon = null,
            onValueChange = { }
        )
        DetailRow(
            "Username",
           updatedValue =  username,
            enable = false,
            textColor = colorResource(R.color.purple_500),
            borderColor = colorResource(R.color.black),
            icon = null,
            onValueChange = { }
        )
        DetailRow(
            "Email",
            updatedValue = email,
            enable = false,
            textColor = colorResource(R.color.purple_500),
            borderColor = colorResource(R.color.black),
            icon = null,
            onValueChange = { }
        )
        DetailRow(
            "Phone",
            updatedValue = phoneNumber,
            enable = false,
            textColor = colorResource(R.color.purple_500),
            borderColor = colorResource(R.color.black),
            icon = null,
            onValueChange = { }
        )

        // Change Password Button
        Box(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
                .border(1.dp, colorResource(R.color.purple_500))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(color = colorResource(R.color.purple_700)),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(vertical = 11.dp),
                    text = "Change Your Password",
                    fontSize = 18.sp,
                    color = colorResource(R.color.white)
                )
                Icon(
                    modifier = Modifier
                        .width(24.dp)
                        .fillMaxHeight()
                        .padding(vertical = 9.dp),
                    painter = painterResource(R.drawable.fast_forward),
                    tint = colorResource(R.color.white),
                    contentDescription = "Change Password"
                )
            }
        }
    }
}

fun navigateToEditProfile(
    navController: NavController,
    fullName: String,
    userName: String,
    emailId: String,
    phone: String
) {
    val route = Route.EditProfileScreen.createRoute(
        fullName = fullName,
        userName = userName,
        emailId = emailId,
        phone = phone
    )
    navController.navigate(route)
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    ProfileScreen(rememberNavController())
}