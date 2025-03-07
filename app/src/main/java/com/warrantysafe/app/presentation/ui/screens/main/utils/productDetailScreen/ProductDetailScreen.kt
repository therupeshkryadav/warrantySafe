package com.warrantysafe.app.presentation.ui.screens.main.utils.productDetailScreen

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.google.gson.Gson
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.utils.Results
import com.warrantysafe.app.presentation.ui.screens.main.profileScreen.components.DetailRow
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.productCardList.components.functions.CustomLinearProgressIndicator
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.productCardList.components.functions.calculateProgress
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.productCardList.components.functions.periodCalculator
import com.warrantysafe.app.presentation.ui.screens.main.utils.categorySection.CategorySection
import com.warrantysafe.app.presentation.ui.screens.main.utils.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.viewModel.ProductViewModel
import com.warrantysafe.app.utils.checkValidNetworkConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.androidx.compose.koinViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun ProductDetailScreen(
    navController: NavController,
    productId: String
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val showReceipt = remember { mutableStateOf(false) }
    val productViewModel: ProductViewModel = koinViewModel()
    val productDetailState = productViewModel.productDetailState.observeAsState(initial = Results.Loading)
    // Remember and update internet connection status dynamically
    val isConnected = remember { mutableStateOf(checkValidNetworkConnection(context)) }
    LaunchedEffect(Unit) {
        productViewModel.loadProductDetail(productId)
    }

    val scrollState = rememberScrollState()
    val currentDate = getCurrentDate()

    when (val result = productDetailState.value) {
        is Results.Loading -> {
            // Show loading state
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Fetching Product Detail .........")
                LinearProgressIndicator()
            }
        }

        is Results.Success -> {
            val product = result.data

            val validPeriod = periodCalculator(
                purchaseDate = product.purchase,
                expiryDate = product.expiry,
                currentDate = currentDate
            )

            val validProgress = calculateProgress(
                product.purchase,
                product.expiry, currentDate
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
                    .statusBarsPadding().navigationBarsPadding()
            ) {
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
                        Log.d("fatal",product.toString())
                        isConnected.value=checkValidNetworkConnection(context)
                        IconButton(onClick = {
                            if(isConnected.value){
                                navigateToEditProductDetailsScreen(
                                    navController = navController,
                                    product= product)
                            }else{
                                Toast.makeText(context, "No Valid Internet Connection!!", Toast.LENGTH_LONG).show()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = "Edit"
                            )
                        }
                    }
                )
                    if (showReceipt.value) {
                        ViewReceiptDialog(
                            receiptUrl = product.receiptImageUri,
                            onDismiss = {
                                showReceipt.value = false // Close the dialog
                            }
                        )
                    }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp)
                        .verticalScroll(scrollState)
                ) {
                    // Handling image loading and displaying with simplified logic

                    Image(
                        painter = rememberAsyncImagePainter(
                            model = product.productImageUri ,
                            placeholder = rememberAsyncImagePainter(product.productImageUri),
                            error = painterResource(id = R.drawable.product_placeholder)
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp)
                            .padding(top = 8.dp, start = 4.dp, end = 4.dp)
                            .border(width = 2.dp, color = colorResource(R.color.black))
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
                                .clickable {
                                    showReceipt.value =true // Call viewReceipt here
                                }
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
                                .clickable {
                                    product.receiptImageUri.let { receiptUrl ->
                                        if (receiptUrl.isNotEmpty()) {
                                            val fileName = "${product.productName}_receipt"
                                            coroutineScope.launch {
                                                downloadAndSavePdf(context, receiptUrl, fileName)
                                            }
                                        } else {
                                            Toast.makeText(context, "No Receipt Available", Toast.LENGTH_LONG).show()
                                        }
                                    }
                                }
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
                        text = validPeriod,
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
                            text = product.notes.ifEmpty { "// your notes would be provided here" },
                            textAlign = TextAlign.Start,
                            fontSize = 16.sp,
                            color = if (product.notes.isEmpty()) Color.LightGray else Color.DarkGray
                        )
                    }
                }
            }
        }

        is Results.Failure -> {
            // Handle failure state
            val errorMessage = result.exception.message ?: "Unknown error"
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                text = "Failed to load product details: $errorMessage",
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }
    }
}

suspend fun downloadAndSavePdf(context: Context, url: String, fileName: String) {
    withContext(Dispatchers.IO) {  // Run on IO thread for performance
        val imageLoader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(url)
            .allowHardware(false) // Needed for bitmap conversion
            .build()

        try {
            val result = (imageLoader.execute(request) as? SuccessResult)?.drawable
            val bitmap = (result as? android.graphics.drawable.BitmapDrawable)?.bitmap

            if (bitmap != null) {
                // Convert image to PDF
                val pdfDocument = PdfDocument()
                val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, 1).create()
                val page = pdfDocument.startPage(pageInfo)
                val canvas = page.canvas
                canvas.drawBitmap(bitmap, 0f, 0f, null)
                pdfDocument.finishPage(page)

                // Save PDF in device's private storage
                // ✅ Try saving in Downloads folder
                val pdfFile = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    "$fileName.pdf"
                )

                FileOutputStream(pdfFile).use { outputStream ->
                    pdfDocument.writeTo(outputStream)
                    pdfDocument.close()
                }

                // Log and notify user
                Log.d("DownloadPdf", "✅ PDF saved at: ${pdfFile.absolutePath}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "PDF saved: ${pdfFile.name}", Toast.LENGTH_LONG).show()
                }
            } else {
                Log.e("DownloadPdf", "❌ Failed to load image.")
            }
        } catch (e: Exception) {
            Log.e("DownloadPdf", "❌ Error saving PDF: ${e.message}", e)
        }
    }
}

@Composable
fun ViewReceiptDialog(
    receiptUrl: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        title = { Text("Receipt") },
        text = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(receiptUrl),
                    contentDescription = "Receipt Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Fit
                )
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}


// Helper function to get the current date in "dd/MM/yyyy" format
@SuppressLint("NewApi")
 fun getCurrentDate(): String {
    return java.time.LocalDate.now()
        .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}

fun navigateToEditProductDetailsScreen(
    navController: NavController,
    product: Product
) {
    // Serialize the product to JSON
    val productJson = Gson().toJson(product)

    // URL encode the productJson string
    val encodedProductJson = URLEncoder.encode(productJson, StandardCharsets.UTF_8.toString())

    // Navigate to the destination with the encoded productJson
    navController.navigate("editProductDetailsScreen/$encodedProductJson")
}

@Preview(showBackground = true)
@Composable
private fun PreviewProductDetailsScreen() {
    ProductDetailScreen(
        navController = rememberNavController(),
        productId = "ProductId"
    )

}