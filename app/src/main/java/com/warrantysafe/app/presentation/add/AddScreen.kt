package com.warrantysafe.app.presentation.add

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
    val showDatePicker = remember { mutableStateOf(false) }
    val selectedDate = remember { mutableStateOf("") }

    if (showDatePicker.value) {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val formattedDate = "$dayOfMonth/${month + 1}/$year"
                selectedDate.value = formattedDate
                showDatePicker.value = false
                // Update the selected date to the respective field
                if (purchaseDate.isEmpty()) {
                    purchaseDate = formattedDate
                } else {
                    expiryDate = formattedDate
                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, bottom = 16.dp)
            .fillMaxSize()
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

        // Product Name Field
        DetailRow(
            label = "Product Name",
            textColor = colorResource(R.color.purple_500),
            enable = true,
            icon = null,
            borderColor = colorResource(R.color.black),
            updatedValue = productName,
            onValueChange = { productName = it } // Update product name dynamically
        )

        // Category Section
        CategorySection()

        // Purchase Date Field
        DetailRow(
            label = "Purchase Date",
            textColor = colorResource(R.color.purple_500),
            enable = false,
            icon = R.drawable.calendar,
            borderColor = colorResource(R.color.black),
            placeHolder = "DD/MM/YYYY",
            updatedValue = purchaseDate,
            onDetailRowClick = {
                showDatePicker.value = true
            },
            onValueChange = { purchaseDate = it } // This handles the case where user types in the field (optional)
        )

        // Expiry Date Field
        DetailRow(
            label = "Expiry Date",
            textColor = colorResource(R.color.purple_500),
            enable = false,
            icon = R.drawable.calendar,
            borderColor = colorResource(R.color.black),
            placeHolder = "DD/MM/YYYY",
            updatedValue = expiryDate,
            onDetailRowClick = {
                showDatePicker.value = true
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

        // Notes Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .border(1.dp, colorResource(R.color.black))
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(all = 8.dp),
                text = "notes would be provided here, if stored!!",
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                color = colorResource(R.color.purple_500)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAdd() {
    AddScreen(rememberNavController())
}

