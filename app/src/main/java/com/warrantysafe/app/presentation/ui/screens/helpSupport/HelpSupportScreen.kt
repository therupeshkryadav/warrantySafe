package com.warrantysafe.app.presentation.ui.screens.helpSupport

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.warrantysafe.app.presentation.ui.screens.common.customTopAppBar.CustomTopAppBar

@Composable
fun HelpSupportScreen(navController: NavController) {
    CustomTopAppBar(
        title =  {
            Text(
                text = "Help & Support",
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
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {}
    )
}