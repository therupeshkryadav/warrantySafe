package com.warrantysafe.app.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize().background(colorResource(R.color.purple_200))
    ){
        Text(
            text = "Welcome to Profile Screen!!", modifier = Modifier
                .fillMaxWidth()
                .basicMarquee().background(colorResource(R.color.white)), fontSize = 12.sp,
            color = colorResource(id = R.color.black)
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview
@Composable
fun ProfilePreview()
{
    ProfileScreen(rememberNavController())
}