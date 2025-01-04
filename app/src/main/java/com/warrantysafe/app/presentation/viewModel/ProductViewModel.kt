package com.warrantysafe.app.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.useCases.AddProductUseCase
import com.warrantysafe.app.domain.useCases.GetProductsUseCase
import kotlinx.coroutines.launch

class ProductViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    private val addProductUseCase: AddProductUseCase
): ViewModel() {
    var products = mutableStateOf<List<Product>>(mutableListOf())

    // Load preloaded greetings when ViewModel is initialized
    fun loadProducts() {
        viewModelScope.launch {
            // Get preloaded greetings from the repository
            val fetchedProducts = getProductsUseCase()
            products.value = fetchedProducts
        }
    }

    fun addProduct(title: String, purchase: String, expiry: String, category: String, imageResId: Int) {
        viewModelScope.launch {
            addProductUseCase(title, purchase, expiry, category, imageResId)
        }
    }
}