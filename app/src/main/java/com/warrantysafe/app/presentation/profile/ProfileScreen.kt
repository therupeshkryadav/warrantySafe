package com.warrantysafe.app.presentation.profile

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.warrantysafe.app.R

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(
                top = 24.dp,
                start = 24.dp,
                end = 24.dp
            )
            .statusBarsPadding()
            .fillMaxSize()
    ){
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Welcome to Profile Screen!!", modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
                .basicMarquee(), fontSize = 12.sp,
            color = colorResource(id = R.color.xtreme2)
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}