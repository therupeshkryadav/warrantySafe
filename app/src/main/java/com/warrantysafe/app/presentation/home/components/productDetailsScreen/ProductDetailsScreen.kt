package com.warrantysafe.app.presentation.home.components.productDetailsScreen

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
import com.warrantysafe.app.presentation.common.productList.components.productCard.components.CustomLinearProgressIndicator
import com.warrantysafe.app.presentation.profile.components.DetailRow

@Composable
fun ProductDetailsScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
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
            value = "LG Washing Machine",
            textColor = colorResource(R.color.purple_500),
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
        DetailRow(
            label = "Purchase Date",
            value = "11/12/2024",
            textColor = colorResource(R.color.purple_500),
            borderColor = colorResource(R.color.black),
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
            text = "Expiry in 0 years 6 months 22 days",
            style = MaterialTheme.typography.labelSmall,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.black)
        )
        CustomLinearProgressIndicator(
            progress = 0.8f,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp)
                .height(28.dp),
            trackColor = colorResource(R.color.xtreme),
            progressColor = colorResource(R.color.DaysLeft),
            strokeWidth = 18f,
            gapSize = 0f,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, colorResource(R.color.black))
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
                text = "Notes would be given here ->",
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                color = colorResource(R.color.purple_500)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewProductDetailsScreen() {
    ProductDetailsScreen(navController = rememberNavController())

}