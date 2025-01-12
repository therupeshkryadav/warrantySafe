package com.warrantysafe.app.presentation.ui.screens.settingsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.presentation.ui.screens.utils.customTopAppBar.CustomTopAppBar

@Composable
fun SettingsScreen(navController: NavController) {

    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopAppBar(
            title = {
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
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            actions = {}
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(8.dp)
                .border(width = 1.dp, Color.Black)
                .background(Color.LightGray),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text("Change Password",
                modifier = Modifier.padding(8.dp),
                fontSize = 12.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(8.dp)
                .border(width = 1.dp, Color.Black)
                .background(Color.LightGray),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                "Manage Categories",
                modifier = Modifier.padding(8.dp),
                fontSize = 12.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(8.dp)
                .border(width = 1.dp, Color.Black)
                .background(Color.LightGray),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text("Change Language",
                modifier = Modifier.padding(8.dp),
                fontSize = 12.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreV() {
    SettingsScreen(rememberNavController())

}