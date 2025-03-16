package com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.termsConditionsScreen

import android.content.Intent
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
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.presentation.ui.screens.main.utils.customTopAppBar.CustomTopAppBar

@Composable
fun TermsConditionsScreen(navController: NavController) {

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
                    text = "Terms & Conditions",
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
                "These terms and conditions apply to the Warranty Safe app (\"Application\") for mobile devices, created by Rupesh Kumar Yadav (\"Service Provider\") as a Free service."
            )

            SectionTitle("Usage Agreement")
            SectionText(
                "By downloading or using the Application, you agree to the following terms. Unauthorized copying, modification, extraction of source code, or creation of derivative versions is prohibited."
            )

            SectionTitle("Data & Security")
            SectionText(
                "The Application stores and processes personal data. It is your responsibility to maintain the security of your device. The Service Provider strongly advises against jailbreaking or rooting your device, as it may lead to security risks."
            )

            SectionTitle("Third-Party Services")
            SectionText("The Application uses third-party services, which have their own Terms & Conditions:")
            Spacer(modifier = Modifier.height(8.dp))
            ClickableLink("Google Play Services", "https://policies.google.com/terms")
            ClickableLink("Google Analytics for Firebase", "https://firebase.google.com/terms")
            ClickableLink("Firebase Crashlytics", "https://firebase.google.com/terms/crashlytics")

            SectionTitle("Connectivity & Charges")
            SectionText(
                "Some features require an active internet connection. If your data allowance is exceeded, you are responsible for any additional charges from your network provider."
            )

            SectionTitle("Updates & Termination")
            SectionText(
                "The Service Provider may update or discontinue the Application at any time. Updates are required to continue using the app, and failure to accept updates may result in loss of functionality."
            )

            SectionTitle("Changes to Terms & Conditions")
            SectionText(
                "The Service Provider may periodically update these Terms. Please check this page regularly for any changes."
            )

            SectionTitle("Contact")
            SectionText("If you have any questions, contact us at:")
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
                val intent = Intent(Intent.ACTION_VIEW, it.item.toUri())
                context.startActivity(intent)
            }
        },
        style = TextStyle(fontSize = 14.sp)
    )
}

@Preview
@Composable
private fun PrevTerms() {
    TermsConditionsScreen(rememberNavController())
}
