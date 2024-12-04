package com.warrantysafe.app.presentation.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.home.Product
import com.warrantysafe.app.presentation.productCard.ProductCard

@Composable
fun ProductList(
    itemTint: Color,
    productType: List<Product> // Changed to a flat list of products
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 48.dp)
    ) {
        items(productType) { product ->
            when (product) {
                is Product.Active -> {
                    ProductCard(
                        title = product.title,
                        purchase = product.purchase,
                        period = product.period,
                        progress = product.progress,
                        imageResId = product.imageResId,
                        progressTint = colorResource(R.color.transparent),
                        itemTint = itemTint
                    )
                }

                is Product.Expired -> {
                    ProductCard(
                        title = product.title,
                        purchase = product.purchase,
                        period = product.period,
                        progress = 1f,
                        progressTint = colorResource(R.color.expired),
                        imageResId = product.imageResId,
                        itemTint = Color.Red
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewProductList() {
    val activeProducts = listOf(
        Product.Active(
            title = "Realme 3 Pro",
            purchase = "30/11/2024",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Realme 7 Pro",
            purchase = "30/11/2024",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Redmi Note 10 ",
            purchase = "30/11/2024",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        )
    )

    ProductList(
        itemTint = Color.Green,
        productType = activeProducts
    )
}
