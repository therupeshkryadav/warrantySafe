package com.warrantysafe.app.presentation.ui.screens.main.utils.productDetailScreen

import android.net.Uri
import android.util.Log
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.productCardList.components.functions.CustomLinearProgressIndicator
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.productCardList.components.functions.calculateProgress
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.productCardList.components.functions.periodCalculator
import com.warrantysafe.app.presentation.ui.screens.main.profileScreen.components.DetailRow
import com.warrantysafe.app.presentation.ui.screens.main.utils.categorySection.CategorySection
import com.warrantysafe.app.presentation.ui.screens.main.utils.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.viewModel.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductDetailScreen(
    navController: NavController,
    productId: String
) {
    val productViewModel: ProductViewModel = koinViewModel()
    LaunchedEffect(Unit) {
        productViewModel.loadProductDetail(productId)
    }
    val productDetailState = productViewModel.productDetailState.observeAsState()
    val scrollState = rememberScrollState()

    val result = productDetailState.value
    // Observe product detail state
    if (result != null) {
        if (result.isSuccess) {
           val product = result.getOrNull()!!

            val validPeriod = periodCalculator(
                purchaseDate = product.purchase,
                expiryDate = product.expiry,
                currentDate = "28/12/2024"
            )

            val validProgress = calculateProgress(
                product.purchase,
                product.expiry, "28/11/2024"
            )


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
                    .statusBarsPadding().navigationBarsPadding()
            ) {
                Log.d("ProfileUpdate", "Fetched Product ID is 2--> $productId , ${product.productName}")
                // Custom Top App Bar
                CustomTopAppBar(
                    title = {
                        Text(
                            text = "Product Card Detail",
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
                        IconButton(onClick = {
                            navigateToEditProductDetailsScreen(
                                navController = navController,
                                productId = product.id,
                                productName = product.productName,
                                purchaseDate = product.purchase,
                                category = product.category,
                                expiryDate = product.expiry,
                                notes = product.notes,
                                imageUri = product.productImageUri.toUri()
                            )
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = "Edit"
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
                Image(
                    painter = rememberAsyncImagePainter(product.productImageUri),
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(280.dp)
                        .padding(8.dp)
                        .border(width = 2.dp, color = colorResource(R.color.black)),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )

                DetailRow(
                    label = "Product Name",
                    updatedValue = product.productName,
                    enable = false,
                    textColor = Color.DarkGray,
                    icon = null,
                    onValueChange = { },
                )

                // Category Section
                CategorySection(
                    updatedCategory = product.category,
                    onSelectEnabled = false,
                    onCategoryChange = {},
                    onCategorySelection = {}
                )
                DetailRow(
                    label = "Purchase Date",
                    updatedValue = product.purchase,
                    enable = false,
                    textColor = Color.DarkGray,
                    icon = R.drawable.calendar,
                    onValueChange = { },
                )
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
                        .padding(bottom = 32.dp)
                        .border(1.dp, colorResource(R.color.black))
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        text = if (product.notes.isEmpty()) "// your notes would be provided here" else product.notes,
                        textAlign = TextAlign.Start,
                        fontSize = 16.sp,
                        color = if (product.notes.isEmpty()) Color.LightGray else Color.DarkGray
                    )
                }
            }
            }
        } else if (result.isFailure) {
            val errorMessage = result.exceptionOrNull()?.message ?: "Unknown error"
            // Handle error state
            Text(
                modifier = Modifier.fillMaxSize().statusBarsPadding().navigationBarsPadding(),
                text = "Failed to load product details: $errorMessage $result",
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        } else{
            // Show loading state
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Loading...", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }

}

fun navigateToEditProductDetailsScreen(
    navController: NavController,
    productId: String,
    productName: String?,
    purchaseDate: String?,
    category: String?,
    expiryDate: String?,
    notes: String?,
    imageUri: Uri? // Image resource ID
) {
    val route = Route.EditProductDetailsScreen.createRoute(
        id = productId,
        productName = productName,
        purchaseDate = purchaseDate,
        category = category,
        expiryDate = expiryDate,
        notes = notes,
        imageUri = imageUri
    )
    navController.navigate(route)
}

@Preview(showBackground = true)
@Composable
private fun PreviewProductDetailsScreen() {
    ProductDetailScreen(
        navController = rememberNavController(),
        productId = "ProductId"
    )

}