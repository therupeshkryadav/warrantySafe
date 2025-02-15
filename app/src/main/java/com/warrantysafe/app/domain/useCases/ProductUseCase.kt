package com.warrantysafe.app.domain.useCases

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.repository.ProductRepository
import com.warrantysafe.app.domain.utils.Results
import com.warrantysafe.app.presentation.ui.screens.main.utils.productDetailScreen.getCurrentDate
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

class AddProductUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(product: Product) {
        // Create new product
        val newProduct = Product(
            productName = product.productName,
            purchase = product.purchase,
            expiry = product.expiry,
            category = product.category,
            productImageUri = product.productImageUri,
            notes = product.notes
        )
        productRepository.addProduct(newProduct)
    }
}

class GetAllProductsUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): Results<List<Product>> {
        return productRepository.getProducts()
    }
}

class GetExpiringWarrantiesUseCase(
    private val repository: ProductRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(daysUntilExpiry: Long): Results<List<Product>> {
        val result = repository.getProducts()

        if (result is Results.Success) {
            val products = result.data
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

            // Filter products whose expiry date is within the specified number of days
            val expiringWarranties = products.filter { product ->
                try {
                    val expiryDate = product.expiry
                    val currentDate = getCurrentDate()
                    val expiry = LocalDate.parse(expiryDate, formatter)
                    val current = LocalDate.parse(currentDate, formatter)
                    val daysToExpiry = current.until(expiry).days

                    daysToExpiry in 0..daysUntilExpiry
                } catch (e: Exception) {
                    false
                }
            }

            return Results.Success(expiringWarranties)
        }

        return Results.Failure(Exception("Failed to get products"))
    }
}


class SearchProductsUseCase(
    private val productRepository: ProductRepository
) {
    suspend fun execute(query: String): List<Product> {
        return productRepository.searchProducts(query)
    }
}

class GetActiveProductsUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(currentDate: String): List<Product> {
        return when (val result = productRepository.getProducts()) {
            is Results.Success -> {
                val products = result.data
                products.filter { product -> isDateBefore(currentDate, product.expiry) }
            }
            is Results.Failure -> {
                // Log the error or handle failure as needed
                emptyList()
            }
            is Results.Loading -> {
                // Handle loading state if necessary
                emptyList()
            }
        }
    }

    @SuppressLint("NewApi")
    private fun isDateBefore(current: String, expiry: String): Boolean {
        val dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
        return try {
            val currentDate = LocalDate.parse(current, dateFormatter)
            val expiryDate = LocalDate.parse(expiry, dateFormatter)
            expiryDate.isAfter(currentDate) // Check if expiry is after current date
        } catch (e: DateTimeParseException) {
            // Log error (you could also throw a custom exception or handle it in other ways)
            false
        }
    }

    companion object {
        const val DATE_FORMAT = "dd/MM/yyyy" // Define a constant for date format
    }
}

class GetExpiredProductsUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(currentDate: String): List<Product> {
        return when (val result = productRepository.getProducts()) {
            is Results.Success -> {
                val products = result.data
                products.filter { product -> isExpired(currentDate, product.expiry) }
            }
            is Results.Failure -> {
                // Log the error or handle failure as needed
                emptyList()
            }
            is Results.Loading -> {
                // Handle loading state if necessary
                emptyList()
            }
        }
    }

    /**
     * Helper function to check if a product's expiry date is before or equal to the current date.
     */
    private fun isExpired(current: String, expiry: String): Boolean {
        val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return try {
            val currentDate = dateFormat.parse(current)
            val expiryDate = dateFormat.parse(expiry)
            expiryDate?.before(currentDate) ?: false // Returns true if expiryDate is before currentDate
        } catch (e: ParseException) {
            false // Consider invalid dates as not expired
        }
    }

    companion object {
        const val DATE_FORMAT = "dd/MM/yyyy" // Shared date format
    }
}

class GetProductDetailUseCase(
    private val productRepository: ProductRepository
) {

    /**
     * Execute the use case to fetch product details.
     *
     * @param productId The ID of the product to fetch.
     * @return Result containing the product details or an error.
     */
    suspend operator fun invoke(productId: String): Results<Product> {
        if (productId.isBlank()) {
            return Results.Failure(Exception("Product ID cannot be blank"))
        }
        return productRepository.getProductDetail(productId)
    }
}

class UpdateProductUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(product: Product) : Results<Product> {
        return try {
            productRepository.updateProduct(product)
        } catch (e: Exception) {
            Results.Failure(e)
        }
    }
}

class DeleteProductsUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(products: List<Product>) = productRepository.deleteProducts(products)
}