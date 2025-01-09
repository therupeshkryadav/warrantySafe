package com.warrantysafe.app.presentation.ui.screens.utils.productDetailScreen.editProductDetailScreen

import android.app.DatePickerDialog
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Refresh
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
import androidx.compose.ui.draw.clip
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
import coil.compose.rememberAsyncImagePainter
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.profileScreen.components.DetailRow
import com.warrantysafe.app.presentation.ui.screens.utils.categorySection.CategorySection
import com.warrantysafe.app.presentation.ui.screens.utils.customTopAppBar.CustomTopAppBar
import java.util.Calendar

@Composable
fun EditProductDetailScreen(
    navController: NavController,
    productName: String? = null,
    purchaseDate: String?,
    category: String? = null,
    expiryDate: String?,
    notes: String? = null,
    imageUri: Uri,
) {
    var validProductName by remember { mutableStateOf(productName) }
    var validPurchaseDate by remember { mutableStateOf(purchaseDate) }
    var validExpiryDate by remember { mutableStateOf(expiryDate) }
    var validNotes by remember { mutableStateOf(notes) }
    var updatedCategory by remember { mutableStateOf(category) }
    val categoryOptions = listOf(
        "General",
        "Electronics",
        "Vehicles",
        "Furniture",
        "Home Appliances",
        "Kitchen Appliances",
        "Gadgets & Accessories",
        "Personal & Lifestyle Products",
        "Tools & Equipment",
        "Health & Medical Devices",
        "Wearables",
        "Others"
    )
    var expanded by remember { mutableStateOf(false) }

    // Create a ScrollState for vertical scrolling
    val scrollState = rememberScrollState()

    // Create a DatePickerDialog callback inside the composable context
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // State for showing the date picker
    val showPurchaseDatePicker = remember { mutableStateOf(false) }
    val showExpiryDatePicker = remember { mutableStateOf(false) }

    // States for selected image URI
    var selectedProductImageUri by remember { mutableStateOf<Uri?>(null) }

    // Activity Result Launcher for Image Picker
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            selectedProductImageUri = uri // Handle success (uri is not null, content was selected)
        } else {
            selectedProductImageUri = null // Handle cancellation (uri is null, no content selected)
        }
    }

    if (showPurchaseDatePicker.value) {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val formattedDate = "$dayOfMonth/${month + 1}/$year"
                validPurchaseDate = formattedDate // Update the purchase date
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
                validExpiryDate = formattedDate // Update the expiry date
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

    val updatedImageUri = selectedProductImageUri?: imageUri

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        // Custom Top App Bar
        CustomTopAppBar(
            title = {
                Text(
                    text = "Edit Product Card Detail",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
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
            actions = {
                IconButton(
                    onClick = {
                        // Clear back stack of Route.EditProductDetailsScreen.route
                        navController.popBackStack(
                            Route.EditProductDetailsScreen.route,
                            inclusive = true
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Edited Successfully!!"
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .verticalScroll(scrollState)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp)
                    .border(width = 2.dp, color = colorResource(R.color.black))
            ) {
                Image(
                    painter = rememberAsyncImagePainter(updatedImageUri),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .padding(2.dp)
                )

                IconButton(
                    onClick = {
                        launcher.launch("image/*") // Open the gallery to select an image
                        //i want to open the gallery and want to select the image and the selected image should be set to the above image composable!!
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                ) {
                    Row(
                        modifier = Modifier.wrapContentWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.refresh_icon),
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(4.dp)) // Add spacing between Icon and Text
                        Text(
                            text = "Change Image",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

            DetailRow(
                label = "Product Name",
                updatedValue = validProductName!!,
                enable = true,
                textColor = Color.DarkGray,
                icon = null,
                onValueChange = { validProductName = it },
            )

            // Category Section
            CategorySection(
                updatedCategory = updatedCategory!!,
                onSelectEnabled = false,
                onCategoryChange = { updatedCategory = it },
                onCategorySelection = { expanded = !expanded }
            )
            // Dropdown Menu
            if (expanded) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                        .padding(vertical = 8.dp)
                ) {
                    Column {
                        categoryOptions.forEach { category ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        updatedCategory = category // Pass selected category
                                        expanded = false // Close dropdown
                                    }
                                    .padding(12.dp)
                                    .background(Color.Transparent)
                            ) {
                                Text(
                                    text = category,
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }

            if (purchaseDate != null) {
                // Purchase Date Field
                DetailRow(
                    label = "Purchase Date",
                    textColor = Color.DarkGray,
                    enable = false,
                    icon = R.drawable.calendar,
                    placeHolder = "DD/MM/YYYY",
                    updatedValue = validPurchaseDate!!,
                    onDetailRowClick = {
                        showPurchaseDatePicker.value = true
                    },
                    onValueChange = {
                        validPurchaseDate = it
                    } // This handles the case where user types in the field (optional)
                )
            }
            if (expiryDate != null) {
                // Expiry Date Field
                DetailRow(
                    label = "Expiry Date",
                    textColor = Color.DarkGray,
                    enable = false,
                    icon = R.drawable.calendar,
                    placeHolder = "DD/MM/YYYY",
                    updatedValue = validExpiryDate!!,
                    onDetailRowClick = {
                        showExpiryDatePicker.value = true
                    },
                    onValueChange = {
                        validExpiryDate = it
                    } // This handles the case where user types in the field (optional)
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
            DetailRow(
                label = "Notes",
                textColor = Color.DarkGray,
                enable = true,
                icon = null,
                placeHolder = "write your notes here -->",
                updatedValue = validNotes!!,
                onDetailRowClick = {
                    showExpiryDatePicker.value = true
                },
                onValueChange = {
                    validNotes = it
                } // This handles the case where user types in the field (optional)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewProductDetailsScreen() {
    EditProductDetailScreen(
        navController = rememberNavController(),
        productName = "LG WASHING MACHINE",
        purchaseDate = "11/01/2023",
        category = "Electronics",
        expiryDate = "11/09/2024",
        imageUri = Uri.parse("android.resource://com.warrantysafe.app/${R.drawable.product_placeholder}")
    )

}