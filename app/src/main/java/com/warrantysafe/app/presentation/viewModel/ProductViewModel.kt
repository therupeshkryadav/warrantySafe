package com.warrantysafe.app.presentation.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.useCases.AddProductUseCase
import com.warrantysafe.app.domain.useCases.DeleteProductsUseCase
import com.warrantysafe.app.domain.useCases.GetActiveProductsUseCase
import com.warrantysafe.app.domain.useCases.GetAllProductsUseCase
import com.warrantysafe.app.domain.useCases.GetExpiredProductsUseCase
import com.warrantysafe.app.domain.useCases.UpdateProductUseCase
import kotlinx.coroutines.launch

class ProductViewModel(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getActiveProductsUseCase: GetActiveProductsUseCase,
    private val getExpiredProductsUseCase: GetExpiredProductsUseCase,
    private val addProductUseCase: AddProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val deleteProductsUseCase: DeleteProductsUseCase
) : ViewModel() {

    private val currentDate = getCurrentDate()

    private val _allProductsState = MutableLiveData<Result<List<Product>>>()
    val allProductsState: LiveData<Result<List<Product>>> get() = _allProductsState

    private val _activeProductsState = MutableLiveData<Result<List<Product>>>()
    val activeProductsState: LiveData<Result<List<Product>>> get() = _activeProductsState

    private val _expiredProductsState = MutableLiveData<Result<List<Product>>>()
    val expiredProductsState: LiveData<Result<List<Product>>> get() = _expiredProductsState

    private val _addProductState = MutableLiveData<Result<Unit>>()
    val addProductState: LiveData<Result<Unit>> get() = _addProductState

    private val _updateProductState = MutableLiveData<Result<Unit>>()
    val updateProductState: LiveData<Result<Unit>> get() = _updateProductState

    private val _deleteProductsState = MutableLiveData<Result<Unit>>()
    val deleteProductsState: LiveData<Result<Unit>> get() = _deleteProductsState

    // Load all products
    fun loadAllProducts() {
        viewModelScope.launch {
            try {
                val products = getAllProductsUseCase()
                _allProductsState.value = products
            } catch (e: Exception) {
                _allProductsState.value = Result.failure(e)
            }
        }
    }

    // Load active products
    fun loadActiveProducts() {
        viewModelScope.launch {
            try {
                val products = getActiveProductsUseCase(currentDate)
                _activeProductsState.value = Result.success(products)
               Log.d("ActiveProducts","Active Products Loaded: $products") // Debugging log
            } catch (e: Exception) {
                _activeProductsState.value = Result.failure(e)
                Log.e("ActiveProducts","Error Loading Active Products: ${e.message}",e) // Debugging log
            }
        }
    }

    // Load expired products
    fun loadExpiredProducts() {
        viewModelScope.launch {
            try {
                val products = getExpiredProductsUseCase(currentDate)
                _expiredProductsState.value = Result.success(products)
            } catch (e: Exception) {
                _expiredProductsState.value = Result.failure(e)
            }
        }
    }

    // Add a new product
    fun addProduct(product: Product) {
        viewModelScope.launch {
            try {
                addProductUseCase(product)
                _addProductState.value = Result.success(Unit)
                refreshAllProducts()
            } catch (e: Exception) {
                _addProductState.value = Result.failure(e)
            }
        }
    }

    // Update an existing product
    fun updateProduct(product: Product) {
        viewModelScope.launch {
            try {
                updateProductUseCase(product)
                _updateProductState.value = Result.success(Unit)
                refreshAllProducts()
            } catch (e: Exception) {
                _updateProductState.value = Result.failure(e)
            }
        }
    }

    // Delete products
    fun deleteProducts(products: List<Product>) {
        viewModelScope.launch {
            try {
                deleteProductsUseCase(products)
                _deleteProductsState.value = Result.success(Unit)
                refreshAllProducts()
            } catch (e: Exception) {
                _deleteProductsState.value = Result.failure(e)
            }
        }
    }

    // Refresh all product lists
    private fun refreshAllProducts() {
        loadAllProducts()
        loadActiveProducts()
        loadExpiredProducts()
    }

    // Helper function to get the current date in "dd/MM/yyyy" format
    @SuppressLint("NewApi")
    private fun getCurrentDate(): String {
        return java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }
}

