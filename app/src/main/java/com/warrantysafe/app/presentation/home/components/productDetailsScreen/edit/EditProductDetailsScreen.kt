package com.warrantysafe.app.presentation.home.components.productDetailsScreen.edit

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.warrantysafe.app.presentation.common.categorySection.CategorySection
import com.warrantysafe.app.presentation.profile.components.DetailRow

@Composable
fun EditProductDetailsScreen(
    navController: NavController,
    productName: String?,
    purchaseDate: String?,
    category: String?,
    expiryDate: String?
) {
    var validProductName by remember { mutableStateOf(productName) }
    var validPurchaseDate by remember { mutableStateOf(purchaseDate) }
    var validExpiryDate by remember { mutableStateOf(expiryDate) }
    // Create a ScrollState for vertical scrolling
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .verticalScroll(scrollState)
    ) {
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
                updatedValue = validProductName!!,
                enable = true,
                textColor = colorResource(R.color.purple_500),
                borderColor = colorResource(R.color.black),
                icon = null,
                onValueChange = { validProductName = it},
            )
        }
        // Category Section
        CategorySection(selectCategory = category, enabled = true)

        if (purchaseDate != null) {
            DetailRow(
                label = "Purchase Date",
                updatedValue = validPurchaseDate!!,
                enable = true,
                textColor = colorResource(R.color.purple_500),
                borderColor = colorResource(R.color.black),
                icon = R.drawable.calendar,
                onValueChange = { validPurchaseDate = it },
            )
        }
        if (expiryDate != null) {
            DetailRow(
                label = "Expiry Date",
                updatedValue = validExpiryDate!!,
                enable = true,
                textColor = colorResource(R.color.purple_500),
                borderColor = colorResource(R.color.black),
                icon = R.drawable.calendar,
                onValueChange = { validExpiryDate = it },
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
                    .background(colorResource(R.color.teal_700))
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
                    text = "Edit",
                    color = colorResource(R.color.white),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Icon(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxHeight(1f),
                    tint = colorResource(R.color.white),
                    painter = painterResource(R.drawable.edit),
                    contentDescription = null
                )
            }
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
                    .wrapContentHeight()
                    .padding(all = 8.dp),
                text = "notes would be provided here,if stored!!",
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                color = colorResource(R.color.purple_500)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewProductDetailsScreen() {
    EditProductDetailsScreen(
        navController = rememberNavController(),
        productName = "LG WASHING MACHINE",
        purchaseDate = "11/01/2023",
        category = "Electronics",
        expiryDate = "11/09/2024"
    )

}