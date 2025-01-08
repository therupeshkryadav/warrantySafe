package com.warrantysafe.app.presentation.ui.screens.addScreen

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.Toast
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
import androidx.compose.material.icons.filled.AddCircle
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.profileScreen.components.DetailRow
import com.warrantysafe.app.presentation.ui.screens.utils.categorySection.CategorySection
import com.warrantysafe.app.presentation.ui.screens.utils.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.viewModel.ProductViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

@SuppressLint("NewApi")
@Composable
fun AddScreen(navController: NavController) {

    val productViewModel: ProductViewModel = koinViewModel()
    var productName by remember { mutableStateOf("") }
    var purchaseDate by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var updatedCategory by remember { mutableStateOf("") }
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
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val purchaseDateSelected = remember { mutableStateOf(false) }
    var purchaseDateLocalDate by remember { mutableStateOf<LocalDate?>(null) } // Store purchase date as LocalDate for comparison

    if (showPurchaseDatePicker.value) {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val localDate = LocalDate.of(year, month + 1, dayOfMonth)
                purchaseDate = dateFormatter.format(localDate)
                purchaseDateLocalDate = localDate // Store selected date
                purchaseDateSelected.value = true
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
                val localDate = LocalDate.of(year, month + 1, dayOfMonth)
                if (purchaseDateLocalDate != null && localDate.isAfter(purchaseDateLocalDate)) {
                    expiryDate = dateFormatter.format(localDate)
                    showExpiryDatePicker.value = false
                } else {
                    Toast.makeText(
                        context,
                        "Expiry Date must be greater than Purchase Date!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
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
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close"
                    )
                }
            },
            actions = {
                IconButton(onClick = {
                    if (productName.isBlank() || purchaseDate.isBlank() || expiryDate.isBlank()) {
                        Toast.makeText(
                            context,
                            "Please fill all required fields.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        productViewModel.addProduct(
                            productName = productName,
                            purchase = purchaseDate,
                            expiry = expiryDate,
                            category = updatedCategory,
                            notes = notes,
                            imageResId = R.drawable.product_placeholder
                        )
                        navController.popBackStack()
                        navController.navigate(Route.HomeScreen.route)
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Add Product Card Icon"
                    )
                }

            }
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxSize()
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
                    painter = painterResource(R.drawable.product_placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                )

                IconButton(
                    onClick = { /* Add your onClick logic here */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                ) {
                    Row(
                        modifier = Modifier.wrapContentWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = "Add Product Image",
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(4.dp)) // Add spacing between Icon and Text
                        Text(
                            text = "Add Product Image",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

            // Product Name Field
            DetailRow(
                label = "Product Name",
                textColor = Color.DarkGray,
                enable = true,
                icon = null,
                placeHolder = "write product name -->",
                updatedValue = productName,
                onValueChange = { productName = it } // Update product name dynamically
            )

            // Category Section
            CategorySection(
                updatedCategory = updatedCategory,
                onSelectEnabled = true,
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

            // Purchase Date Field
            DetailRow(
                label = "Purchase Date",
                textColor = Color.DarkGray,
                enable = false,
                icon = R.drawable.calendar,
                placeHolder = "DD/MM/YYYY",
                updatedValue = purchaseDate,
                onDetailRowClick = {
                    showPurchaseDatePicker.value = true
                },
                onValueChange = {
                    purchaseDate = it
                } // This handles the case where user types in the field (optional)
            )
            // Expiry Date Field
            DetailRow(
                label = "Expiry Date",
                textColor = Color.DarkGray,
                enable = false,
                icon = R.drawable.calendar,
                placeHolder = "DD/MM/YYYY",
                updatedValue = expiryDate,
                onDetailRowClick = {
                    if (purchaseDateSelected.value) {
                        showExpiryDatePicker.value = true
                    } else {
                        Toast.makeText(context, "Please select Purchase Date first!", Toast.LENGTH_SHORT).show()
                    }
                },
                onValueChange = {
                    expiryDate = it
                } // This handles the case where user types in the field (optional)
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
                placeHolder = "write your notes here -->",
                updatedValue = notes,
                onValueChange = {
                    notes = it
                } // This handles the case where user types in the field (optional)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


