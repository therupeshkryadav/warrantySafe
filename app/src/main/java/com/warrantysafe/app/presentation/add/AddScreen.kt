package com.warrantysafe.app.presentation.add

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navController: NavController) {

    var productName by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    // Create a ScrollState for vertical scrolling
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxSize()
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

        Spacer(modifier = Modifier
            .background(color = colorResource(R.color.black))
            .fillMaxWidth()
            .height(1.dp))

        Column(modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(scrollState)
        ) {
            OutlinedTextField(
                value = productName,
                enabled = true,
                textStyle = TextStyle.Default.copy(fontSize = 16.sp),
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 8.dp)
                    .height(60.dp),
                onValueChange = { productName = it },
                label = { Text(fontSize = 16.sp, text = "Product name") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = colorResource(R.color.black),
                    focusedLabelColor = colorResource(R.color.xtreme2),
                    unfocusedLabelColor = colorResource(R.color.xtreme),
                    focusedBorderColor = colorResource(R.color.xtreme2),
                    unfocusedBorderColor = colorResource(R.color.black),
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp),
                    text = "Category :",
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.black)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clip(shape = RoundedCornerShape(2.dp))
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
                            text = "Electronics",
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

            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .height(48.dp)
                        .clip(shape = RoundedCornerShape(2.dp))
                        .background(colorResource(R.color.green))
                        .border(width = 1.dp, color = colorResource(R.color.black))

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(start = 48.dp), // Set a fixed height to align items properly
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(0.6f), // Use weight to distribute space equally
                            textAlign = TextAlign.Start,
                            text = "Upload Receipt Image",
                            fontSize = 16.sp,
                            maxLines = 1
                        )
                        Icon(
                            modifier = Modifier
                                .padding(start = 2.dp)
                                .fillMaxHeight(1f),
                            painter = painterResource(R.drawable.upload),
                            contentDescription = null
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp),
                        text = "Purchase Date:",
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.black)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .clip(shape = RoundedCornerShape(2.dp))
                            .border(width = 1.dp, color = colorResource(R.color.black))
                    ) {
                        Row {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(0.85f)
                                    .padding(start = 16.dp, top = 12.dp),
                                text = "DD / MM / YYYY",
                                textAlign = TextAlign.Start,
                                fontSize = 16.sp,
                                color = colorResource(id = R.color.xtreme)
                            )
                            Icon(
                                modifier = Modifier
                                    .width(24.dp)
                                    .padding(end = 8.dp)
                                    .fillMaxHeight(1f),
                                painter = painterResource(R.drawable.calendar),
                                contentDescription = null
                            )
                        }
                    }

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp),
                        text = "Expiration Date:",
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.black)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .clip(shape = RoundedCornerShape(2.dp))
                            .border(width = 1.dp, color = colorResource(R.color.black))
                    ) {
                        Row {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(0.85f)
                                    .padding(start = 16.dp, top = 12.dp),
                                text = "DD / MM / YYYY",
                                textAlign = TextAlign.Start,
                                fontSize = 16.sp,
                                color = colorResource(id = R.color.xtreme)
                            )
                            Icon(
                                modifier = Modifier
                                    .width(24.dp)
                                    .padding(end = 8.dp)
                                    .fillMaxHeight(1f),
                                painter = painterResource(R.drawable.calendar),
                                contentDescription = null
                            )
                        }
                    }
                }
            }

            OutlinedTextField(
                value = notes,
                enabled = true,
                textStyle = TextStyle.Default.copy(fontSize = 16.sp),
                modifier = Modifier
                    .fillMaxSize(1f)
                    .padding(bottom = 24.dp),
                onValueChange = { notes = it },
                placeholder = {
                    Text(
                        modifier = Modifier.fillMaxSize(1f),
                        text = "//Something about the product ...",
                        color = colorResource(R.color.xtreme)
                    )
                },
                label = { Text(fontSize = 16.sp, text = "Notes") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = colorResource(R.color.black),
                    focusedLabelColor = colorResource(R.color.xtreme2),
                    unfocusedLabelColor = colorResource(R.color.xtreme),
                    focusedBorderColor = colorResource(R.color.xtreme2),
                    unfocusedBorderColor = colorResource(R.color.black),
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAdd() {
    AddScreen(rememberNavController())
}
