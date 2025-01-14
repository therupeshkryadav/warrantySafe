package com.warrantysafe.app.presentation.ui.screens.loginSignUpScreen.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.warrantysafe.app.R

@Composable
fun SignUpPage(
    onSignUpSuccess: () -> Unit
) {
    // Remember state for user input
    val username = remember { mutableStateOf("") }
    val fullName = remember { mutableStateOf("") }
    val emailAddress = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(8.dp).wrapContentSize()) {

        // Profile Avatar
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(color = colorResource(R.color.black))
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(R.drawable.profile_placeholder),
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

        // EmailAddress Field
        TextField(
            value = emailAddress.value,
            onValueChange = { emailAddress.value = it },
            modifier = Modifier
                .fillMaxWidth().border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(20.dp)),
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

        // PhoneNumber Field
        TextField(
            value = phoneNumber.value,
            onValueChange = { phoneNumber.value = it },
            modifier = Modifier
                .fillMaxWidth().border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(20.dp)),
            placeholder = {
                Text("Enter Your Phone Number", color = Color.Gray)
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.phone_pp),
                    contentDescription = "PhoneNumber Icon"
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

        //Login Button
        Button(
            onClick = { onSignUpSuccess() },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "SignUp with credentials")
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PrevSignUpPage() {
    SignUpPage(onSignUpSuccess = {})
}