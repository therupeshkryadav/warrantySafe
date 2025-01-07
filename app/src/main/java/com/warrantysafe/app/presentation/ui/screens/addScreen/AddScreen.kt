package com.warrantysafe.app.presentation.ui.screens.addScreen

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.profileScreen.components.DetailRow
import com.warrantysafe.app.presentation.ui.screens.utils.categorySection.CategorySection
import com.warrantysafe.app.presentation.ui.screens.utils.customTopAppBar.CustomTopAppBar
import java.util.Calendar

@Composable
fun AddScreen(navController: NavController) {

    var productName by remember { mutableStateOf("") }
    var purchaseDate by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    // Create a ScrollState for vertical scrolling
    val scrollState = rememberScrollState()

    // Create a DatePickerDialog callback inside the composable context
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // State for showing the date picker
    val showPurchaseDatePicker = remember { mutableStateOf(false) }
    val showExpiryDatePicker = remember { mutableStateOf(false) }

    if (showPurchaseDatePicker.value) {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val formattedDate = "$dayOfMonth/${month + 1}/$year"
                purchaseDate = formattedDate // Update the purchase date
                showPurchaseDatePicker.value = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            setOnCancelListener {
                showPurchaseDatePicker.value = false // Dismiss dialog without updating date
            }
        }.show()
    }

    if (showExpiryDatePicker.value) {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val formattedDate = "$dayOfMonth/${month + 1}/$year"
                expiryDate = formattedDate // Update the expiry date
                showExpiryDatePicker.value = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            setOnCancelListener {
                showExpiryDatePicker.value = false // Dismiss dialog without updating date
            }
        }.show()
    }

    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxSize()
    ) {
        CustomTopAppBar(
            title = {
                Text(
                    text = "Add Product",
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
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close"
                    )
                }
            },
            actions = {
                IconButton(onClick = {
                    // Clear back stack of Route.AddScreen.route
                    navController.popBackStack(Route.AddScreen.route, inclusive = true)

                    navController.navigate(Route.HomeScreen.route)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Check Icon"
                    )
                }
            }
        )
        Image(
            painter = painterResource(R.drawable.item_image_placeholder),
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(280.dp)
                .padding(8.dp)
                .border(width = 2.dp, color = colorResource(R.color.black)),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .padding(horizontal =  8.dp)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Product Name Field
            DetailRow(
                label = "Product Name",
                textColor = Color.DarkGray,
                enable = true,
                icon = null,
                placeHolder = "write product name -->",
                borderColor = colorResource(R.color.black),
                updatedValue = productName,
                onValueChange = { productName = it } // Update product name dynamically
            )

            // Category Section
            CategorySection()

            // Purchase Date Field
            DetailRow(
                label = "Purchase Date",
                textColor = Color.DarkGray,
                enable = false,
                icon = R.drawable.calendar,
                borderColor = colorResource(R.color.black),
                placeHolder = "DD/MM/YYYY",
                updatedValue = purchaseDate,
                onDetailRowClick = {
                    showPurchaseDatePicker.value = true
                },
                onValueChange = { purchaseDate = it } // This handles the case where user types in the field (optional)
            )

            // Expiry Date Field
            DetailRow(
                label = "Expiry Date",
                textColor = Color.DarkGray,
                enable = false,
                icon = R.drawable.calendar,
                borderColor = colorResource(R.color.black),
                placeHolder = "DD/MM/YYYY",
                updatedValue = expiryDate,
                onDetailRowClick = {
                    showExpiryDatePicker.value = true
                },
                onValueChange = { expiryDate = it } // This handles the case where user types in the field (optional)
            )

            // Upload Receipt Image Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(R.color.body))
                    .border(width = 1.dp, color = colorResource(R.color.black))
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center,
                    color = colorResource(R.color.white),
                    text = "Upload Receipt Image",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1
                )
                Icon(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxHeight(1f),
                    tint = colorResource(R.color.white),
                    painter = painterResource(R.drawable.upload),
                    contentDescription = null
                )
            }
            DetailRow(
                label = "Notes",
                textColor = Color.DarkGray,
                enable = true,
                icon = null,
                borderColor = colorResource(R.color.black),
                placeHolder = "write your notes here -->",
                updatedValue = notes,
                onDetailRowClick = {
                    showExpiryDatePicker.value = true
                },
                onValueChange = { notes = it } // This handles the case where user types in the field (optional)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAdd() {
    AddScreen(rememberNavController())
}

