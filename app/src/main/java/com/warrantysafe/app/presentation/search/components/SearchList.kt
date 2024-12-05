import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.common.productList.components.productCard.ProductCard
import com.warrantysafe.app.presentation.home.Product

@Composable
fun SearchList(
    matchedList: List<Product>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 8.dp, start = 8.dp, end = 8.dp)
    ) {
        // Dynamically filter the input list for Active and Expired products
        val activeList = matchedList.filterIsInstance<Product.Active>()
        val expiredList = matchedList.filterIsInstance<Product.Expired>()

        // Display Active Products
        items(activeList.size) { index ->
            val product = activeList[index]
            ProductCard(
                title = product.title,
                purchase = product.purchase,
                period = product.period,
                progress = product.progress,
                imageResId = product.imageResId,
                progressTint = colorResource(R.color.DaysLeft),
                itemTint = colorResource(R.color.transparent),
                detailsColor = MaterialTheme.colorScheme.onSurface
            )
        }

        // Display Expired Products
        items(expiredList.size) { index ->
            val product = expiredList[index]
            ProductCard(
                title = product.title,
                purchase = product.purchase,
                period = product.period,
                progress = product.progress,
                imageResId = product.imageResId,
                progressTint = colorResource(R.color.noDaysLeft),
                itemTint = colorResource(R.color.expired),
                detailsColor = MaterialTheme.colorScheme.inversePrimary
            )
        }
    }
}
