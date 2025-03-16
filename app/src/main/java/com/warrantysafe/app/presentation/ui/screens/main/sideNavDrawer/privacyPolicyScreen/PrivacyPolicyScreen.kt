package com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.privacyPolicyScreen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.presentation.ui.screens.main.utils.customTopAppBar.CustomTopAppBar

@Composable
fun PrivacyPolicyScreen(navController: NavController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        CustomTopAppBar(
            title = {
                Text(
                    text = "Privacy Policy",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            actions = {}
        )

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            SectionText(
                "This privacy policy applies to the Warranty Safe app (\"Application\") created by Rupesh Kumar Yadav (\"Service Provider\") as a Free service."
            )

            SectionTitle("Information Collection and Use")
            SectionText(
                "The Application collects information such as your IP address, pages visited, time spent on pages, and your device's operating system."
            )

            SectionTitle("Data Usage")
            SectionText(
                "The Service Provider may use your information to contact you for important updates, notices, or marketing promotions."
            )

            SectionTitle("Third-Party Access")
            SectionText(
                "The Application shares anonymized data with third-party services for performance improvements. Below are the links to their privacy policies:"
            )
            Spacer(modifier = Modifier.height(8.dp))
            ClickableLink("Google Play Services", "https://policies.google.com/privacy")
            ClickableLink("Google Analytics for Firebase", "https://firebase.google.com/support/privacy")
            ClickableLink("Firebase Crashlytics", "https://firebase.google.com/terms/crashlytics")

            SectionTitle("Opt-Out Rights")
            SectionText(
                "You can stop all data collection by uninstalling the Application from your device."
            )

            SectionTitle("Data Retention Policy")
            SectionText(
                "The Service Provider retains user data as long as you use the Application. You may request data deletion by contacting:"
            )
            ClickableLink("rupeshkryadav484@gmail.com", "mailto:rupeshkryadav484@gmail.com")

            SectionTitle("Children's Privacy")
            SectionText(
                "The Application does not knowingly collect personal data from children under 13. If you believe a child has provided information, please contact us for deletion."
            )

            SectionTitle("Security")
            SectionText(
                "The Service Provider takes security seriously and employs safeguards to protect user data."
            )

            SectionTitle("Changes to Privacy Policy")
            SectionText(
                "The Privacy Policy may be updated from time to time. Continued use of the Application implies acceptance of any changes."
            )

            SectionTitle("Your Consent")
            SectionText(
                "By using the Application, you consent to the processing of your data as outlined in this Privacy Policy."
            )

            SectionTitle("Contact Us")
            SectionText("For any privacy concerns, contact us at:")
            ClickableLink("rupeshkryadav484@gmail.com", "mailto:rupeshkryadav484@gmail.com")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun SectionText(content: String) {
    Text(
        text = content,
        style = TextStyle(fontSize = 14.sp, color = MaterialTheme.colorScheme.onBackground),
        textAlign = TextAlign.Justify,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun ClickableLink(text: String, url: String) {
    val context = LocalContext.current
    val annotatedString = buildAnnotatedString {
        pushStringAnnotation(tag = "URL", annotation = url)
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(text)
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations("URL", offset, offset).firstOrNull()?.let {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.item))
                context.startActivity(intent)
            }
        },
        style = TextStyle(fontSize = 14.sp)
    )
}

@Preview
@Composable
private fun PrevPrivacy() {
    PrivacyPolicyScreen(rememberNavController())
}
