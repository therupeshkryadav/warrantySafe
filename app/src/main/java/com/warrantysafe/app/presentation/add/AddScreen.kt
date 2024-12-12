package com.warrantysafe.app.presentation.add

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.profile.components.DetailRow

@Composable
fun AddScreen(navController: NavController) {

    var productName by remember { mutableStateOf("") }
    var purchaseDate by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    // Create a ScrollState for vertical scrolling
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
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
        DetailRow(
            label = "Product Name",
            initialValue = productName,
            textColor = colorResource(R.color.purple_500),
            enable = true,
            icon = null,
            borderColor = colorResource(R.color.black),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .padding(end = 8.dp),
                text = "Category :",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.black)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(shape = RoundedCornerShape(2.dp))
                    .clickable {  }
                    .border(width = 1.dp, color = colorResource(R.color.black))

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(colorResource(R.color.xtreme)), // Set a fixed height to align items properly
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.9f) // Use weight to distribute space equally
                            .padding(start = 16.dp),
                        textAlign = TextAlign.Start,
                        text = "General",
                        fontSize = 16.sp
                    )
                    Icon(
                        modifier = Modifier
                            .width(24.dp)
                            .fillMaxHeight(1f),
                        painter = painterResource(R.drawable.drop_down),
                        contentDescription = null
                    )
                }
            }

        }
        DetailRow(
            label = "Purchase Date",
            initialValue = purchaseDate,
            textColor = colorResource(R.color.purple_500),
            enable = true,
            icon = R.drawable.calendar,
            borderColor = colorResource(R.color.black),
        )
        DetailRow(
            label = "Expiry Date",
            initialValue = expiryDate,
            textColor = colorResource(R.color.purple_500),
            enable = true,
            icon = R.drawable.calendar,
            borderColor = colorResource(R.color.black),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.body))
                .border(
                    width = 1.dp,
                    color = colorResource(R.color.black)
                )
                .padding(horizontal = 8.dp, vertical = 8.dp), // Set a fixed height to align items properly
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically), // Use weight to distribute space equally
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
fun PreviewAdd() {
    AddScreen(rememberNavController())
}
