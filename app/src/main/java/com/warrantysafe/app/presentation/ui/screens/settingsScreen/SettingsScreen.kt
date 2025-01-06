package com.warrantysafe.app.presentation.ui.screens.settingsScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.warrantysafe.app.presentation.ui.screens.utils.customTopAppBar.CustomTopAppBar

@Composable
fun SettingsScreen(navController: NavController) {
    CustomTopAppBar(
        title =  {
            Text(
                text = "Settings",
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
        actions = {}
    )
}