package com.warrantysafe.app.presentation.ui.screens.loginSignUpScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.ui.screens.loginSignUpScreen.tabs.LoginPage
import com.warrantysafe.app.presentation.ui.screens.loginSignUpScreen.tabs.SignUpPage
import kotlinx.coroutines.launch

@Composable
fun LoginSignUpScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp, start = 8.dp, end = 8.dp)
    ) {
        val tabTitles = listOf("Login", "SignUp")

        val pagerState = rememberPagerState(
            initialPage = 0,
            pageCount = { tabTitles.size }
        )
        val scope = rememberCoroutineScope()

        // Custom TabRow with no click effect
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = colorResource(R.color.transparent),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            tabTitles.forEachIndexed { index, title ->
                // Custom Tab Implementation
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                        .background(colorResource(R.color.transparent))
                        .clip(RoundedCornerShape(8.dp)) // Optional for styling
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null // Disables ripple effect
                        ) {
                            scope.launch { pagerState.animateScrollToPage(index) }
                        }
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        // HorizontalPager for the content
        HorizontalPager(
            state = pagerState, // Synchronizes with TabRow
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.Top
        ) { page ->
            // Content for each page
            when (page) {
                0 -> LoginPage(navController = navController) // Content for Login Tab
                1 -> SignUpPage(navController = navController) // Content for SignUp Tab
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginSignUpScreen() {
    LoginSignUpScreen(
       navController = rememberNavController()
    )
}
