package com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.aboutApp

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.warrantysafe.app.presentation.ui.screens.main.utils.customTopAppBar.CustomTopAppBar

@Composable
fun AboutAppScreen(navController: NavController) {

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CustomTopAppBar(
                title = {
                    Text(
                        text = "About the App",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        maxLines = 1
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
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
                .padding(horizontal = 16.dp)
        ) {
            item {
                Text(
                    text = "WarrantySafe",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )
                Text(
                    text = "WarrantySafe is an Android application developed using Kotlin and Jetpack Compose, designed to help users manage their product warranties efficiently. With WarrantySafe, users can store, track, and receive reminders about warranty expiration dates, ensuring they never miss a claim opportunity.",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            item {
                Text(
                    text = "Features",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            val features = listOf(
                "• Store Warranties: Save warranty details such as product name, purchase date, expiration date, and store details.",
                "• Receipt & Warranty Upload: Attach scanned copies or photos of receipts and warranty documents.",
                "• Reminder Notifications: Get alerts before a warranty expires.",
                "• Categorization: Organize warranties based on product type.",
                "• Search & Filter: Easily find warranties by name, date, or category.",
                "• Backup & Restore: Securely back up warranty data to cloud storage for easy retrieval."
            )

            items(features) { feature ->
                Text(
                    text = feature,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            item {
                Text(
                    text = "Tech Stack",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            val techStack = listOf(
                "• Language: Kotlin",
                "• Architecture: Clean Architecture",
                "• UI Framework: Jetpack Compose",
                "• Dependency Injection: Koin",
                "• Cloud Sync: Cloud Firestore, Appwrite",
                "• Notifications: WorkManager"
            )

            items(techStack) { tech ->
                Text(
                    text = tech,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            item {
                Text(
                    text = "Usage",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            val usage = listOf(
                "• Add a new warranty: Enter product details and attach warranty proof.",
                "• Set reminders: Configure notifications for expiration dates.",
                "• Backup & Restore: Sync data to Firebase for safety.",
                "• Search & Manage: Use search functionality to find warranties quickly."
            )

            items(usage) { step ->
                Text(
                    text = step,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            item {
                Text(
                    text = "License",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = "This project is licensed under the GNU GENERAL PUBLIC LICENSE.",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            item {
                Text(
                    text = "Contact",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            item {
                Column {
                    ClickableLink(
                        label = "Email: rupeshkryadav484@gmail.com",
                        url = "mailto:rupeshkryadav484@gmail.com"
                    )
                    ClickableLink(
                        label = "GitHub: therupeshkryadav",
                        url = "https://github.com/therupeshkryadav"
                    )
                    ClickableLink(
                        label = "LinkedIn: therupeshkryadav",
                        url = "https://linkedin.com/in/therupeshkryadav"
                    )
                }
            }

            item {
                Text(
                    text = "🚀 Start organizing your warranties today with WarrantySafe!",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                )
            }
        }
    }
}

@Composable
fun ClickableLink(label: String, url: String) {
    val context = LocalContext.current

    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                append(label)
            }
        },
        fontSize = 16.sp,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
    )
}
