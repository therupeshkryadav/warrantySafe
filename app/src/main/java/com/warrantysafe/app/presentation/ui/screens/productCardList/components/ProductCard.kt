package com.warrantysafe.app.presentation.ui.screens.productCardList.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.ui.screens.productCardList.components.functions.CustomLinearProgressIndicator
import com.warrantysafe.app.presentation.ui.screens.productCardList.components.functions.calculateProgress
import com.warrantysafe.app.presentation.ui.screens.productCardList.components.functions.periodCalculator

@Composable
fun ProductCard(
    onClick: () -> Unit, // Callback to handle click and pass details
    onLongPress: () -> Unit,
    productName: String,
    itemTint: Color,
    category:String,
    detailsColor: Color,
    purchase: String,
    expiry: String,
    imageResId: Int // Image resource ID
) {
    val period = periodCalculator(
        purchaseDate = purchase,
        expiryDate = expiry,
        currentDate = "28/12/2024"
    )
    val progress = calculateProgress(purchase, expiry, "28/12/2024")
    var isSelected by remember { mutableStateOf(false) } // State to track selection

    Box(modifier = Modifier.wrapContentSize().padding(vertical = 16.dp).pointerInput(Unit) {
        detectTapGestures(
            onPress = {
                val startPress = System.currentTimeMillis()
                tryAwaitRelease()
                if (System.currentTimeMillis() - startPress > 500) { // Long press threshold (in ms)
                    isSelected = !isSelected
                    onLongPress()
                }
            },
            onTap = {
                onClick() // Regular click functionality
            }
        )
    }) {

        // Category tag positioned above the card
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .zIndex(1f) // Ensures it is drawn above the card
                .offset(y= (-12).dp,x=(-18).dp)
                .background(Color.Yellow, shape = RoundedCornerShape(8.dp))
                .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 4.dp) //text Box design
        ) {
            Text(
                text = category,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .clickable { onClick() }
                .background(color = itemTint)
                .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(20.dp)),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(0.35f)
                        .fillMaxHeight(1f)
                        .clip(shape = RectangleShape),
                    colorFilter = ColorFilter.tint(
                        color = colorResource(R.color.expired), // Change this to your desired color
                        blendMode = BlendMode.Color
                    ),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                ) {
                    Text(
                        text = productName,
                        style = MaterialTheme.typography.titleMedium,
                        color = detailsColor
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Purchase Date: $purchase",
                        style = MaterialTheme.typography.bodySmall,
                        color = detailsColor.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Expiry in $period",
                        style = MaterialTheme.typography.labelSmall,
                        color = detailsColor.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    if (progress != null) {
                        CustomLinearProgressIndicator(
                            progress = progress,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(28.dp),
                            trackColor = colorResource(R.color.white),
                            progressColor = if (progress >= 1f)
                                colorResource(R.color.noDaysLeft)
                            else
                                colorResource(R.color.DaysLeft),
                            strokeWidth = 18f,
                            gapSize = 0f,
                        )
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    ProductCard(
        onClick = {},
        onLongPress = {},
        productName = "Realme 3 Pro",
        purchase = "30/11/2023",
        expiry = "01/12/2025",
        category = "Electronics",
        itemTint = Color.Transparent,
        detailsColor = Color.Black,
        imageResId = R.drawable.product_placeholder // Replace with your drawable resource
    )
}