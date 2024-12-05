package com.warrantysafe.app.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
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
import com.warrantysafe.app.presentation.profile.components.DetailRow


@Composable
fun ProfileScreen(navController: NavController) {
    val fullName = "Rupesh Kumar Yadav"
    val email = "rupesh.official484@gmail.com"
    val phoneNumber = "7233966649"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        // Edit Profile Icon
        Box(modifier = Modifier.fillMaxWidth()) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterEnd),
                painter = painterResource(R.drawable.edit),
                contentDescription = "Edit Profile"
            )
        }

        // Profile Avatar
        Box(
            modifier = Modifier
                .size(244.dp)
                .clip(CircleShape)
                .background(color = colorResource(R.color.black))
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(R.drawable.profile_avatar),
                modifier = Modifier
                    .size(240.dp)
                    .align(Alignment.Center)
                    .clip(CircleShape),
                contentDescription = "Profile Avatar",
                contentScale = ContentScale.Crop
            )
        }

        // Profile Details
        DetailRow("Name", fullName, textColor = colorResource(R.color.purple_500), borderColor = colorResource(R.color.black))
        DetailRow("Email", email, textColor = colorResource(R.color.purple_500), borderColor = colorResource(R.color.black))
        DetailRow("Phone", phoneNumber, textColor = colorResource(R.color.purple_500), borderColor = colorResource(R.color.black))

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


@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    ProfileScreen(rememberNavController())
}