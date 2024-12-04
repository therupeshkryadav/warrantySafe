package com.warrantysafe.app.presentation.productCard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.productCard.components.CustomLinearProgressIndicator


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCard(
    title: String,
    itemTint: Color,
    progressTint: Color,
    period: String,
    purchase: String,
    progress: Float, // Progress value from 0f to 1f
    imageResId: Int // Image resource ID
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = itemTint),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.xtreme)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.35f)
                    .fillMaxHeight(1f)
                    .padding(end = 16.dp)
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
                    .fillMaxWidth()
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Purchase Date: $purchase",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Expiry in $period",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.height(8.dp))

                CustomLinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(28.dp),
                    trackColor = colorResource(R.color.white),
                    progressColor = progressTint,
                    strokeWidth = 18f,
                    gapSize = 0f,
                )
            }
        }
    }
}

@Preview
@Composable
fun ProductCardPreview() {
    MaterialTheme {
        ProductCard(
            title = "Realme 3 Pro",
            purchase = "30/11/2024",
            period = "1 years 0 months 0 days",
            progress = 0.9f,
            itemTint = colorResource(R.color.expired),
            progressTint = colorResource(R.color.expired),
            imageResId = R.drawable.item_image_placeholder // Replace with your drawable resource
        )
    }
}