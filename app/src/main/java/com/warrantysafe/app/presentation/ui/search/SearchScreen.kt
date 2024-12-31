package com.warrantysafe.app.presentation.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.ui.home.Product
import com.warrantysafe.app.presentation.ui.search.components.searchList.SearchList

@Composable
fun SearchScreen(navController: NavController) {
    // Content under the TopAppBar
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val matchedList = listOf(
            Product.Active(
                title = "Realme 3 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product.Active(
                title = "Realme 7 Pro",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product.Active(
                title = "Redmi Note 10 ",
                purchase = "30/11/2024",
                expiry = "30/11/2025",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product.Expired(
                title = "Rado Watch",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product.Expired(
                title = "PS5",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            ),
            Product.Expired(
                title = "LG Washing Machine ",
                purchase = "30/11/2023",
                expiry = "01/12/2024",
                category = "Electronics",
                imageResId = R.drawable.item_image_placeholder
            )
        )

        if (!matchedList.isEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween, // Distribute components to start and end
                verticalAlignment = Alignment.CenterVertically // Center items vertically
            ) {
                // First Box (Sort By Section)
                Box(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.surface)
                        .border(
                            width = 1.dp,
                            shape = RectangleShape,
                            color = colorResource(R.color.black)
                        )
                        .padding(start = 8.dp) // Padding for spacing within the Box
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically // Align items vertically
                    ) {
                        Text(
                            text = "Sort By",
                            modifier = Modifier.padding(end = 4.dp) // Space between text and icon
                        )
                        Icon(
                            modifier = Modifier
                                .size(24.dp), // Define a consistent size for the icon
                            painter = painterResource(R.drawable.drop_down),
                            contentDescription = null
                        )
                    }
                }

                // Second Box (Filter Section)
                Box(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.surface)
                        .border(
                            width = 1.dp, shape = RectangleShape,
                            color = colorResource(R.color.black)
                        )
                        .padding(end = 8.dp) // Padding for spacing within the Box
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically // Align items vertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(24.dp) // Define a consistent size for the icon
                                .padding(start = 8.dp), // Space between icon and text
                            painter = painterResource(R.drawable.filter),
                            contentDescription = null
                        )
                        Text(
                            text = "Filter"
                        )
                    }
                }
            }
        }
//        val matchedList = emptyList<Product>()
        SearchList(navController = navController, matchedList = matchedList)

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchScreen() {
    SearchScreen(rememberNavController())
}
