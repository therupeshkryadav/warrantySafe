package com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.productCardList.components

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.productCardList.components.functions.CustomLinearProgressIndicator
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.productCardList.components.functions.calculateProgress
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.productCardList.components.functions.periodCalculator

@Composable
fun ProductCard(
    onClick: () -> Unit,
    onSlidingForward: () -> Unit,
    onSlidingBackward: () -> Unit,
    productName: String,
    itemTint: Color,
    category: String,
    detailsColor: Color,
    purchase: String,
    expiry: String,
    imageUri: String
) {
    val currentDate = getCurrentDate()
    val period = periodCalculator(purchase, expiry, currentDate)
    val progress = calculateProgress(purchase, expiry, currentDate)

    val finalImageUri = if (imageUri.isNotEmpty()) imageUri.toUri()
    else Uri.parse("android.resource://com.warrantysafe.app/${R.drawable.product_placeholder}")
Box(modifier = Modifier.padding(top = 16.dp)){
    // Category tag positioned above the card
    Box(
        modifier = Modifier
            .align(Alignment.TopEnd)
            .zIndex(1f) // Ensures it is drawn above the card
            .offset(y = (-6).dp, x = (-20).dp)
            .shadow(8.dp, RoundedCornerShape(8.dp))
            .background(Color.Gray, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 4.dp) //text Box design
    ) {
        Text(
            text = category,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            style = MaterialTheme.typography.bodySmall
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 6.dp)
            .shadow(8.dp, shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp,Color.Black.copy(alpha = 0.2f), RoundedCornerShape(20.dp))
            .background(Color.White)
            .clickable { onClick() }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    if (dragAmount > 0) onSlidingForward() else onSlidingBackward()
                }
            }
    ) {

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = finalImageUri,
                        placeholder = painterResource(id = R.drawable.product_placeholder),
                        error = painterResource(id = R.drawable.product_placeholder)
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .zIndex(1f)
                        .border(0.4.dp,Color.LightGray, CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = productName, style = MaterialTheme.typography.titleMedium, color = detailsColor)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Purchase Date: $purchase", style = MaterialTheme.typography.bodySmall, color = detailsColor.copy(alpha = 0.7f))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = period, fontSize = 10.sp,  style = MaterialTheme.typography.labelSmall, color = detailsColor.copy(alpha = 0.7f))
                    Spacer(modifier = Modifier.height(12.dp))

                    progress?.let {
                        CustomLinearProgressIndicator(
                            progress = it,
                            modifier = Modifier.fillMaxWidth(),
                            trackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                            progressColor = if (it >= 1f) Color.Red else MaterialTheme.colorScheme.primary,
                            strokeWidth = 14f,
                            gapSize = 0f,
                        )
                    }
                }
            }
        }
    }}
}


// Helper function to get the current date in "dd/MM/yyyy" format
@SuppressLint("NewApi")
private fun getCurrentDate(): String {
    return java.time.LocalDate.now()
        .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    ProductCard(
        onClick = {},
        onSlidingForward = {},
        onSlidingBackward = {},
        productName = "Realme 3 Pro",
        itemTint = Color.Transparent,
        category = "Electronics",
        detailsColor = Color.Black,
        purchase = "30/11/2023",
        expiry = "01/12/2025",
        imageUri = ""
    )
}