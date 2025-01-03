package com.warrantysafe.app.presentation.ui.screens.home.components.productDetailsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.ui.screens.common.categorySection.CategorySection
import com.warrantysafe.app.presentation.ui.screens.common.productList.components.productCard.components.CustomLinearProgressIndicator
import com.warrantysafe.app.presentation.ui.screens.common.productList.components.productCard.components.calculateProgress
import com.warrantysafe.app.presentation.ui.screens.common.productList.components.productCard.components.periodCalculator
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.profile.components.DetailRow

@Composable
fun ProductDetailsScreen(
    navController: NavController,
    productName: String?,
    purchaseDate: String?,
    category: String?,
    expiryDate: String?
) {
    val validProductName = productName ?: "Unknown Product"
    val validPurchaseDate = purchaseDate ?: "Not Available"
    val validExpiryDate = expiryDate ?: "Not Available"

    val validPeriod = periodCalculator(
        purchaseDate = validPurchaseDate,
        expiryDate = validExpiryDate,
        currentDate = "28/12/2024"
    )
    val validProgress = calculateProgress(validPurchaseDate, validExpiryDate, "28/11/2024")


    var scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .verticalScroll(scrollState)
    ) {
        // Edit Product Detail Icon
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterEnd)
                    .clickable {
                        navigateToEditProductDetailsScreen(
                            navController = navController,
                            productName = productName,
                            purchaseDate = purchaseDate,
                            category = category,
                            expiryDate = expiryDate
                        )
                    },
                painter = painterResource(R.drawable.edit),
                contentDescription = "Edit Profile"
            )
        }
        Image(
            painter = painterResource(R.drawable.item_image_placeholder),
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(280.dp)
                .padding(top = 8.dp, bottom = 8.dp)
                .border(width = 2.dp, color = colorResource(R.color.black)),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        if (productName != null) {
            DetailRow(
                label = "Product Name",
                updatedValue = validProductName,
                enable = false,
                textColor = colorResource(R.color.purple_500),
                borderColor = colorResource(R.color.black),
                icon = null,
                onValueChange = { },
            )
        }
        // Category Section
        CategorySection(selectCategory = category, enabled = false)

        if (purchaseDate != null) {
            DetailRow(
                label = "Purchase Date",
                updatedValue = validPurchaseDate,
                enable = false,
                textColor = colorResource(R.color.purple_500),
                borderColor = colorResource(R.color.black),
                icon = R.drawable.calendar,
                onValueChange = { },
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .height(48.dp)
                .clip(shape = RoundedCornerShape(2.dp))
                .border(width = 1.dp, color = colorResource(R.color.black)),
            horizontalArrangement = Arrangement.spacedBy(0.dp)

        ) {
            Row(
                modifier = Modifier
                    .weight(0.5f)
                    .background(colorResource(R.color.green))
                    .border(
                        width = 1.dp,
                        color = colorResource(R.color.black)
                    )
                    .padding(horizontal = 8.dp), // Set a fixed height to align items properly
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically), // Use weight to distribute space equally
                    textAlign = TextAlign.Center,
                    text = "View Receipt Image",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1
                )
                Icon(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxHeight(1f),
                    painter = painterResource(R.drawable.view_receipt),
                    contentDescription = null
                )
            }
            Row(
                modifier = Modifier
                    .background(colorResource(R.color.teal_200))
                    .border(
                        width = 1.dp,
                        color = colorResource(R.color.black)
                    )
                    .padding(horizontal = 8.dp), // Set a fixed height to align items properly
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier, // Use weight to distribute space equally
                    textAlign = TextAlign.End,
                    text = "Download",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Icon(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxHeight(1f),
                    painter = painterResource(R.drawable.download_receipt),
                    contentDescription = null
                )
            }
        }
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "Expiry in $validPeriod",
            style = MaterialTheme.typography.labelSmall,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.black)
        )
        if (validProgress != null) {
            CustomLinearProgressIndicator(
                progress = validProgress,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp)
                    .height(28.dp),
                trackColor = colorResource(R.color.xtreme),
                progressColor = if (validProgress > 0.99f)
                    colorResource(R.color.noDaysLeft)
                else
                    colorResource(R.color.DaysLeft),
                strokeWidth = 18f,
                gapSize = 0f,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .border(1.dp, colorResource(R.color.black))
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "notes would be provided here,if stored!!",
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                color = colorResource(R.color.purple_500)
            )
        }
    }
}

fun navigateToEditProductDetailsScreen(
    navController: NavController,
    productName: String?,
    purchaseDate: String?,
    category: String?,
    expiryDate: String?
) {
    val route = Route.EditProductDetailsScreen.createRoute(
        productName = productName,
        purchaseDate = purchaseDate,
        category = category,
        expiryDate = expiryDate,
    )
    navController.navigate(route)
}

@Preview(showBackground = true)
@Composable
private fun PreviewProductDetailsScreen() {
    ProductDetailsScreen(
        navController = rememberNavController(),
        productName = "LG WASHING MACHINE",
        purchaseDate = "11/01/2023",
        category = "Electronics",
        expiryDate = "11/09/2024"
    )

}